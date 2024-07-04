package com.example.springboot.controller;


import com.example.springboot.Utils.MessageFactory;
import com.example.springboot.Utils.TokenUtils;
import com.example.springboot.common.Result;
import com.example.springboot.entity.*;
import com.example.springboot.request.DectectionPageRequest;
import com.example.springboot.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

@Slf4j
/**
 * @author oy
 */
@RestController
@RequestMapping("/detection")
public class DetectionController {

    @Autowired
    IDetectionService dectectionService;
    @Autowired
    IImageService imageService;
    @Autowired
    IVideoService videoService;
    @Autowired
    IAudioService audioService;
    @Autowired
    MessageFactory messageFactory;

    private static final Pattern SUFFIX_PATTERN = Pattern.compile("\\.(jpg|mp4|mp3|png)$");
    private static final String MODEL_PATH = "C:\\Users\\86138\\Desktop\\笔记集\\项目\\breakice\\Model\\FaceForensics\\classification\\detect_from_video.py";
    private static final String OUTPUT_FOLDER = "C:\\Users\\86138\\Desktop\\笔记集\\项目\\breakice\\Model\\FaceForensics\\classification\\output\\";
    private static final Random RANDOM = new Random();

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
            dectectionService.startDetectionForVideo(fileId, pid);
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

    @PostMapping("/makeForImage")
    public void makeForImage(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        for (Integer fileId : fileIds) {
            Image byId = imageService.getById(fileId);
            Detection detection = dectectionService.getByPath(byId.getPath());

            dectectionService.runDetectionLocal(detection.getLocalpath(), MODEL_PATH, OUTPUT_FOLDER);
            Detection detection1 = dectectionService.readTxtFile(OUTPUT_FOLDER + "Result", detection);
//            User currentUser = TokenUtils.getCurrentUser();
//            String token = TokenUtils.genToken(currentUser.getId().toString(), currentUser.getKeynum());
//            detection1.setPath("http://10.195.154.158:9090/api/image/download/" + flag + "?token=" + token);
            update(detection1);
            imageService.deleteById(byId.getId());

            messageFactory.createMessage(detection.getPid(),"检测通知","您的最新图像检测已完成,检测号为" + detection.getCardnum());
        }
    }

    @PostMapping("/makeForVideo")
    public void makeForVideo(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        for (Integer fileId : fileIds) {
            Video byId = videoService.getById(fileId);
            Detection detection = dectectionService.getByPath(byId.getPath());
            dectectionService.runDetectionLocal(detection.getLocalpath(), MODEL_PATH, OUTPUT_FOLDER);
            Detection detection1 = dectectionService.readTxtFile(OUTPUT_FOLDER + "Result", detection);

            update(detection1);
            videoService.deleteById(byId.getId());

            messageFactory.createMessage(detection.getPid(),"检测通知","您的最新视频检测已完成,检测号为" + detection.getCardnum());
        }
    }

    @PostMapping("/makeForAudio")
    public void makeForAudio(@RequestBody Map<String, Object> payload) {
        List<Integer> fileIds = (List<Integer>) payload.get("fileIds");
        for (Integer fileId : fileIds) {
            Audio byId = audioService.getById(fileId);
            Detection detection = dectectionService.getByPath(byId.getPath());
            dectectionService.runDetectionLocal(detection.getLocalpath(), MODEL_PATH, OUTPUT_FOLDER);
            Detection detection1 = dectectionService.readTxtFile(OUTPUT_FOLDER + "Result", detection);

            update(detection1);
            audioService.deleteById(byId.getId());

            messageFactory.createMessage(detection.getPid(),"检测通知","您的最新图像检测已完成,检测号为" + detection.getCardnum());
        }
    }

}

