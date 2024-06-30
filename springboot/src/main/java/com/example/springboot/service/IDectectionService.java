package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Dectection;
import com.example.springboot.entity.User;
import com.example.springboot.request.BaseRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author oy
 */
public interface IDectectionService extends IService<Dectection> {

    void startDetectionForImage(Integer fileId, Integer pid);

    void startDetectionForVideo(Integer fileId, Integer pid);

    void startDetectionForAudioSingle(Integer fileId, Integer pid);

    void startDetectionForAudioDouble(Integer fileId, Integer pid);

    boolean save(Dectection dectection);

    Dectection getById(Integer id);

    void update(Dectection dectection);

    void deleteById(Integer id);

    List<Dectection> list();

    PageInfo<Dectection> page(BaseRequest baseRequest);
}


