package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.service.IDectectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author oy
 */
@RestController
@RequestMapping("/detection")
public class DectectionController {

    @Autowired
    IDectectionService dectectionService;

    @PostMapping("/createForImage")
    public Result createForImage(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        Integer pid = (Integer) payload.get("pid");
        System.out.println("fileIds: " + fileIds);
        System.out.println("pid: " + pid);

        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForImage(fileId, pid);
        }

        return Result.success();
    }

    @PostMapping("/createForVideo")
    public Result createForVideo(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        Integer pid = (Integer) payload.get("pid");
        System.out.println("fileIds: " + fileIds);
        System.out.println("pid: " + pid);

        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForVideo(fileId, pid);
        }
        return Result.success();
    }

    @PostMapping("/createForAudioSingle")
    public Result createForAudioSingle(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        Integer pid = (Integer) payload.get("pid");
        System.out.println("fileIds: " + fileIds);
        System.out.println("pid: " + pid);

        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForAudioSingle(fileId, pid);
        }
        return Result.success();
    }

    @PostMapping("/createForAudioSingle")
    public Result createForAudioDouble(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        Integer pid = (Integer) payload.get("pid");
        System.out.println("fileIds: " + fileIds);
        System.out.println("pid: " + pid);

        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForAudioDouble(fileId, pid);
        }
        return Result.success();
    }
}

