package com.example.springboot.Utils;

import com.example.springboot.entity.Detection;
import com.example.springboot.mapper.DetectionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class DetectionFactory {

    @Autowired
    DetectionMapper detectionMapper;

    public Detection create(Integer pid, String style, String name, String path){

        return getDectection(pid, style, name, path, detectionMapper);
    }

    public static Detection getDectection(Integer pid, String style, String name, String path, DetectionMapper detectionMapper) {
        Detection detection = new Detection();
        detection.setName(name);
        detection.setPath(path);
        detection.setCreatetime(new Date());
        detection.setUpdatetime(new Date());
        detection.setStyle(style);
        detection.setStatus("等待检测");
        detection.setPid(pid);
        detection.setCardnum(generateCardNum());
        detectionMapper.save(detection);

        return detection;
    }

    private static String generateCardNum() {
        String datePart = new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
        String uuidPart = String.valueOf(Math.abs(UUID.randomUUID().toString().hashCode()));
        return datePart + uuidPart;
    }
}

