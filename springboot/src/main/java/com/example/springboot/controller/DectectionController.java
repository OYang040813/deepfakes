package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.service.IDectectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author oy
 */
@RestController
@RequestMapping("/detection")
public class DectectionController {

    @Autowired
    IDectectionService dectectionService;

    @PostMapping("/createForImage")
    public Result createForImage(@RequestParam List<Integer> fileIds, @RequestParam Integer pid) {
        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForImage(fileId, pid);
        }
        return Result.success();
    }

    @PostMapping("/createForVideo")
    public Result createForVideo(@RequestParam List<Integer> fileIds, @RequestParam Integer pid) {
        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForVideo(fileId, pid);
        }
        return Result.success();
    }

    @PostMapping("/createForAudioSingle")
    public Result createForAudioSingle(@RequestParam List<Integer> fileIds, @RequestParam Integer pid) {
        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForAudioSingle(fileId, pid);
        }
        return Result.success();
    }
}

