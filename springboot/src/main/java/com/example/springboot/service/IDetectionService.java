package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Detection;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @author oy
 */
public interface IDetectionService extends IService<Detection> {

    void startDetectionForImage(Integer fileId, Integer pid);

    void download(String flag, String play, HttpServletResponse response);

    Result startDetectionForVideo(Integer fileId, Integer pid);

    void startDetectionForAudioSingle(Integer fileId, Integer pid);

    void startDetectionForAudioDouble(Integer fileId, Integer pid);

    boolean save(Detection detection);

    Detection getById(Integer id);

    void update(Detection detection);

    void deleteById(Integer id);

    List<Detection> list();

    PageInfo<Detection> page(BaseRequest baseRequest);

    Result runDetectionCloud(String filePath);

    Result runDetectionLocal(String inputVideo, String modelPath, String outputFolder);
}


