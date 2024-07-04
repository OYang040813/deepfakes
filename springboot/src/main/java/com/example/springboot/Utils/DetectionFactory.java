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

    public Detection create(Integer pid, String style, String name, String path, String localpath, Integer id){

        return getDectection(pid, style, name, path, localpath, detectionMapper, id);
    }

    public static Detection getDectection(Integer pid, String style, String name, String path, String localpath, DetectionMapper detectionMapper, Integer id) {
        Detection detection = new Detection();
        detection.setName(name);
        detection.setPath(path);
        detection.setLocalpath(localpath);
        detection.setCreatetime(new Date());
        detection.setUpdatetime(new Date());
        detection.setStyle(style);
        detection.setStatus("等待检测");
        detection.setPid(pid);
        detection.setCardnum(generateCardNum());
        detection.setFileid(id);
        detectionMapper.save(detection);

        return detectionMapper.getByFileid(id);
    }

    private static String generateCardNum() {
        String datePart = new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
        String uuidPart = String.valueOf(Math.abs(UUID.randomUUID().toString().hashCode()));
        return datePart + uuidPart;
    }
}

