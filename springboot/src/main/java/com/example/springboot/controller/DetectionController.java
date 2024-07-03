package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Detection;
import com.example.springboot.request.DectectionPageRequest;
import com.example.springboot.service.IDetectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
/**
 * @author oy
 */
@RestController
@RequestMapping("/detection")
public class DetectionController {

    @Autowired
    IDetectionService dectectionService;

    @PostMapping("/createForImage")
    public Result createForImage(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        Integer pid = (Integer) payload.get("pid");

        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForImage(fileId, pid);
        }

        return Result.success();
    }

    @PostMapping("/createForVideo")
    public Result createForVideo(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        Integer pid = (Integer) payload.get("pid");

        for (Integer fileId : fileIds) {
            Result result = dectectionService.startDetectionForVideo(fileId, pid);
        }
        return Result.success();
    }

    @PostMapping("/createForAudioSingle")
    public Result createForAudioSingle(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        Integer pid = (Integer) payload.get("pid");

        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForAudioSingle(fileId, pid);
        }
        return Result.success();
    }

    @PostMapping("/createForAudioDouble")
    public Result createForAudioDouble(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        Integer pid = (Integer) payload.get("pid");

        for (Integer fileId : fileIds) {
            dectectionService.startDetectionForAudioDouble(fileId, pid);
        }
        return Result.success();
    }

    //////////////////////////////////////////////////////////////////////////
    @PostMapping("/save")
    public Result save(@RequestBody Detection detection) {
        dectectionService.save(detection);
        return Result.success();
    }
    
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        dectectionService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Detection detection) {
        dectectionService.update(detection);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Detection detection = dectectionService.getById(id);
        return Result.success(detection);
    }

    @GetMapping("/list")
    public Result list() {
        List<Detection> detections = dectectionService.list();
        return Result.success(detections);
    }

    @GetMapping("/page")
    public Result page(DectectionPageRequest dectectionPageRequest) {
        return Result.success(dectectionService.page(dectectionPageRequest));
    }

}

