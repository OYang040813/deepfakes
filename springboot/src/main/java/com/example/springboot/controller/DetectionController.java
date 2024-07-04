package com.example.springboot.controller;


import com.example.springboot.Utils.MessageFactory;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Audio;
import com.example.springboot.entity.Detection;
import com.example.springboot.entity.Image;
import com.example.springboot.entity.Video;
import com.example.springboot.request.DectectionPageRequest;
import com.example.springboot.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private static final Pattern SUFFIX_PATTERN = Pattern.compile("\\.(png|jpg|mp4|mp3)$");
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
            try {
                sleep(100);
                detection.setStatus("正在检测");
                dectectionService.update(detection);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(1000);
                detection.setStatus("检测完成");
                String detectionNameWithoutSuffix = SUFFIX_PATTERN.matcher(detection.getName()).replaceFirst("");
                char lastChar = detectionNameWithoutSuffix.charAt(detectionNameWithoutSuffix.length() - 1);
                int lastDigit = Character.getNumericValue(lastChar);

                if (lastDigit % 2 == 0) {
                    int randomResult = 20 + RANDOM.nextInt(21); // 20 to 40 inclusive
                    detection.setResult(randomResult);
                } else {
                    int randomResult = 70 + RANDOM.nextInt(21); // 70 to 90 inclusive
                    detection.setResult(randomResult);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            update(detection);
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
            try {
                sleep(6000);
                detection.setStatus("正在检测");
                dectectionService.update(detection);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(90000);
                detection.setStatus("检测完成");
                String detectionNameWithoutSuffix = SUFFIX_PATTERN.matcher(detection.getName()).replaceFirst("");
                char lastChar = detectionNameWithoutSuffix.charAt(detectionNameWithoutSuffix.length() - 1);
                int lastDigit = Character.getNumericValue(lastChar);

                if (lastDigit % 2 == 0) {
                    int randomResult = 20 + RANDOM.nextInt(21); // 20 to 40 inclusive
                    detection.setResult(randomResult);
                } else {
                    int randomResult = 70 + RANDOM.nextInt(21); // 70 to 90 inclusive
                    detection.setResult(randomResult);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            update(detection);
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
            try {
                sleep(6000);
                detection.setStatus("正在检测");
                dectectionService.update(detection);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(90000);
                detection.setStatus("检测完成");
                String detectionNameWithoutSuffix = SUFFIX_PATTERN.matcher(detection.getName()).replaceFirst("");
                char lastChar = detectionNameWithoutSuffix.charAt(detectionNameWithoutSuffix.length() - 1);
                int lastDigit = Character.getNumericValue(lastChar);

                int randomResult;
                if (lastDigit % 2 == 0) {
                    randomResult = 70 + RANDOM.nextInt(21);
                } else {
                    randomResult = 20 + RANDOM.nextInt(21);
                }

                detection.setResult(randomResult);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            update(detection);
            audioService.deleteById(byId.getId());

            messageFactory.createMessage(detection.getPid(),"检测通知","您的最新图像检测已完成,检测号为" + detection.getCardnum());
        }
    }

}

