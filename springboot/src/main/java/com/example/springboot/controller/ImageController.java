package com.example.springboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.Utils.TokenUtils;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Image;
import com.example.springboot.entity.Image;
import com.example.springboot.entity.User;
import com.example.springboot.request.ImagePageRequest;
import com.example.springboot.request.UserPageRequest;
import com.example.springboot.service.IImageService;
import com.example.springboot.service.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author oy
 */
@Slf4j
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    IImageService imageService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/DectetionFiles/image/";

    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        long flag = System.currentTimeMillis();//时间戳
        String filePath = BASE_FILE_PATH + flag + "_" + originalFilename;
        try {
            FileUtil.mkParentDirs(filePath); // 创建父级目录
            file.transferTo(FileUtil.file(filePath));
            Image image = new Image();
            image.setName(originalFilename);

            //打包Token与文件路径一并传回前端
            User currentUser = TokenUtils.getCurrentUser();
            String token = TokenUtils.genToken(currentUser.getId().toString(), currentUser.getKeynum());

            image.setPath("http://localhost:9090/api/image/download/" + flag + "?token=" + token);
            image.setStatus("-1");
            imageService.save(image);

            Image image1 = imageService.getByPath(image.getPath());

            return Result.success(image1);
        } catch (Exception e){
            log.error("文件上传失败",e);
        }
        return Result.error("文件上传失败");
    }

    @GetMapping("/download/{flag}")
    public void download(@PathVariable String flag, @RequestParam(required = false) String play,
                         HttpServletResponse response) throws UnsupportedEncodingException {
        OutputStream os;
        List<String> filenames = FileUtil.listFileNames(BASE_FILE_PATH);
        String fileName = filenames.stream().filter(name -> name.contains(flag)).findAny().
                orElse("");
        try {
            if (StrUtil.isNotEmpty(fileName)){
                String realName = fileName.substring(fileName.indexOf("_") + 1);
                if("1".equals(play)){
                    response.addHeader("Content-Disposition","inline;filename=" +
                            URLEncoder.encode(realName, "UTF-8"));
                } else {
                    response.addHeader("Content-Disposition","attachment;filename=" +
                            URLEncoder.encode(realName, "UTF-8"));
                }
                byte[] bytes = FileUtil.readBytes(BASE_FILE_PATH + fileName);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            log.error("文件下载失败",e);
        }
    }

    @PostMapping("/save")
    public Result save(@RequestBody Image image) {
        imageService.save(image);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        imageService.deleteById(id);
        return Result.success();
    }


    @PutMapping("/update")
    public Result update(@RequestBody Image image) {
        imageService.update(image);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Image image = imageService.getById(id);
        return Result.success(image);
    }

    @GetMapping("/list")
    public Result list() {
        List<Image> users = imageService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(ImagePageRequest imagePageRequest) {
        return Result.success(imageService.page(imagePageRequest));
    }

    @DeleteMapping("/deleteFile")
    public Result deleteFileByName(@RequestParam String url, @RequestParam String name) {
        try {
            // 从 URL 中提取 flag 部分
            String flag = url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));
            List<String> filenames = FileUtil.listFileNames(BASE_FILE_PATH);
            String targetFileName = filenames.stream()
                    .filter(fileName -> fileName.contains(flag) && fileName.contains(name))
                    .findAny()
                    .orElse("");
            if (StrUtil.isNotEmpty(targetFileName)) {
                FileUtil.del(BASE_FILE_PATH + targetFileName);
                return Result.success("文件删除成功");
            } else {
                return Result.error("文件不存在");
            }
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return Result.error("文件删除失败");
        }
    }
}

