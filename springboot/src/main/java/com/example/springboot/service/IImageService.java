package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Image;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author oy
 */
public interface IImageService extends IService<Image> {

    Result upload(MultipartFile file, String userId);

    void download(String flag, String play, HttpServletResponse response);

    Result deleteFile(String url, String name);

    List<Image> list(Integer id);

    PageInfo<Image> page(BaseRequest baseRequest);

    boolean save(Image image);

    Image getById(Integer id);

    void update(Image image);

    void deleteById(Integer id);

    Image getByName(String name);

    Image getByPath(String path);

    void owner(Integer pid, Integer id);

}


