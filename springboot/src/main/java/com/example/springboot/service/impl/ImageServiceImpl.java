package com.example.springboot.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.Utils.TokenUtils;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Image;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.ImageMapper;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.service.IImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author oy
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

    @Autowired
    ImageMapper imageMapper;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/DetectionFiles/image/";

    @Override
    public Result upload(MultipartFile file, String userId){
        if (userId == null) {
            return Result.error("userId 参数不能为空");
        }
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

            image.setPath("http://10.195.154.158:9090/api/image/download/" + flag + "?token=" + token);
            image.setLocalpath(filePath);
            image.setStatus("-1");
            image.setPid(Math.toIntExact(Long.parseLong(userId)));
            imageMapper.save(image);
            Image image1 = getByPath(image.getPath());

            return Result.success(image1);
        } catch (Exception e){
            log.error("文件上传失败",e);
        }
        return Result.error("文件上传失败");
    }

    @Override
    public void download(String flag, String play, HttpServletResponse response) {
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

        @Override
        public Result deleteFile(String url, String name) {
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

    @Override
    public List<Image> list(Integer id) {
        return imageMapper.list(id);
    }
    
    @Override
    public PageInfo<Image> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Image> images = imageMapper.listByCondition(baseRequest);
        return new PageInfo<>(images);
    }

    @Override
    public boolean save(Image image) {
        
        image.setUpdatetime(new Date());
        imageMapper.save(image);

        return false;
    }

    @Override
    public Image getById(Integer id) {
        return imageMapper.getById(id);
    }

    @Override
    public void update(Image image) {
        image.setUpdatetime(new Date());
        imageMapper.updateById(image);
    }

    @Override
    public void deleteById(Integer id) {
        imageMapper.deleteById(id);
    }

    @Override
    public Image getByName(String name) {
        return imageMapper.getByName(name);
    }

    @Override
    public Image getByPath(String path) {
        return imageMapper.getByPath(path);
    }

    @Override
    public void owner(Integer pid, Integer id) {
        Image byId = imageMapper.getById(id);
        byId.setPid(pid);
        imageMapper.updateById(byId);
    }
}


