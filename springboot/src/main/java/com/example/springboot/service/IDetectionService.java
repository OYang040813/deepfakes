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

    Result startDetectionForImage(Integer fileId, Integer pid);

    void download(String flag, String play, HttpServletResponse response);

    Result startDetectionForVideo(Integer fileId, Integer pid);

    Result startDetectionForAudioSingle(Integer fileId, Integer pid);

    void startDetectionForAudioDouble(Integer fileId, Integer pid);

    boolean save(Detection detection);

    Detection getById(Integer id);

    void update(Detection detection);

    void deleteById(Integer id);

    List<Detection> list();

    PageInfo<Detection> page(BaseRequest baseRequest);

    Result runDetectionCloud(String filePath);

    Result runDetectionLocal(String inputVideo, String modelPath, String outputFolder);

    Detection readTxtFile(String filePath, Detection detection);

    Detection getByFileId(Integer fileId);

    Detection getByName(String name);

    Detection getByPath(String path);
}


