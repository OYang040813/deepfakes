package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Dectection;
import com.example.springboot.request.DectectionPageRequest;
import com.example.springboot.service.IDectectionService;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/createForAudioDouble")
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

    //////////////////////////////////////////////////////////////////////////
    @PostMapping("/save")
    public Result save(@RequestBody Dectection dectection) {
        dectectionService.save(dectection);
        return Result.success();
    }
    
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        dectectionService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Dectection dectection) {
        dectectionService.update(dectection);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Dectection dectection = dectectionService.getById(id);
        return Result.success(dectection);
    }

    @GetMapping("/list")
    public Result list() {
        List<Dectection> dectections = dectectionService.list();
        return Result.success(dectections);
    }

    @GetMapping("/page")
    public Result page(DectectionPageRequest dectectionPageRequest) {
        return Result.success(dectectionService.page(dectectionPageRequest));
    }

    @PostMapping("/predict")
    public String predict(@RequestParam("filePath") String filePath){
        String url = "在线服务的调用地址";
        String token = "用户Token";

        File file = new File(filePath);
        if (!file.exists()) {
            return "Error: File not found";
        }

        try {
            // Create post request
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("X-Auth-Token", token);

            HttpEntity entity = MultipartEntityBuilder.create()
                    .addBinaryBody("images", file)
                    .setContentType(ContentType.MULTIPART_FORM_DATA)
                    .setCharset(Consts.UTF_8)
                    .build();
            httpPost.setEntity(entity);

            // Send post request
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // Get response
                return EntityUtils.toString(response.getEntity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}

