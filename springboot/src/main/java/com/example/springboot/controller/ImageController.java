package com.example.springboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.Utils.TokenUtils;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Image;
import com.example.springboot.entity.User;
import com.example.springboot.request.ImagePageRequest;
import com.example.springboot.service.IImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
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

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId){
        return imageService.upload(file,userId);
    }

    @GetMapping("/download/{flag}")
    public void download(@PathVariable String flag, @RequestParam(required = false) String play,
                         HttpServletResponse response) {
       imageService.download(flag,play,response);
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

    @GetMapping("/list/{id}")
    public Result list(@PathVariable String id) {
        List<Image> images = imageService.list(Integer.valueOf(id));
        return Result.success(images);
    }

    @PutMapping("/owner")
    public Result owner(@RequestParam Integer pid, @RequestParam Integer id) {
        imageService.owner(pid, id);
        return Result.success();
    }

    @GetMapping("/page")
    public Result page(ImagePageRequest imagePageRequest) {
        return Result.success(imageService.page(imagePageRequest));
    }

    @DeleteMapping("/deleteFile")
    public Result deleteFileByName(@RequestParam String url, @RequestParam String name) {
        return imageService.deleteFile(url,name);
    }
}

