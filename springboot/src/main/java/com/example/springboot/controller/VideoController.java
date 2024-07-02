package com.example.springboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.Utils.TokenUtils;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.entity.Video;
import com.example.springboot.request.VideoPageRequest;
import com.example.springboot.service.IVideoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author oy
 */
@Slf4j
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    IVideoService videoService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/DetectionFiles/video/";

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId){
        if (userId == null) {
            return Result.error("userId 参数不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        long flag = System.currentTimeMillis(); // 时间戳
        String filePath = BASE_FILE_PATH + flag + "_" + originalFilename;
        try {
            FileUtil.mkParentDirs(filePath); // 创建父级目录
            file.transferTo(FileUtil.file(filePath));
            Video video = new Video();
            video.setName(originalFilename);

            //打包Token与文件路径一并传回前端
            User currentUser = TokenUtils.getCurrentUser();
            String token = TokenUtils.genToken(currentUser.getId().toString(), currentUser.getKeynum());

            video.setPath("http://localhost:9090/api/video/download/" + flag + "?token=" + token);
            video.setStatus("-1");
            video.setPid(Math.toIntExact(Long.parseLong(userId)));
            video.setLocalpath(filePath);
            videoService.save(video);

            Video video1 = videoService.getByPath(video.getPath());

            return Result.success(video1);
        } catch (Exception e){
            log.error("文件上传失败", e);
        }
        return Result.error("文件上传失败");
    }

    @GetMapping("/download/{flag}")
    public void download(@PathVariable String flag, @RequestParam(required = false) String play,
                         HttpServletResponse response) {
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
        } catch (ClientAbortException e) {
            log.warn("客户端中止了连接: {}", e.getMessage());
        } catch (IOException e) {
            log.error("文件下载失败", e);
        }
    }



    @PostMapping("/save")
    public Result save(@RequestBody Video video) {
        videoService.save(video);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        videoService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Video video) {
        videoService.update(video);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Video video = videoService.getById(id);
        return Result.success(video);
    }

    @GetMapping("/list/{id}")
    public Result list(@PathVariable String id) {
        List<Video> videos = videoService.list(Integer.valueOf(id));
        return Result.success(videos);
    }

    @PutMapping("/owner")
    public Result owner(@RequestParam Integer pid, @RequestParam Integer id) {
        videoService.owner(pid, id);
        return Result.success();
    }

    @GetMapping("/page")
    public Result page(VideoPageRequest videoPageRequest) {
        return Result.success(videoService.page(videoPageRequest));
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

