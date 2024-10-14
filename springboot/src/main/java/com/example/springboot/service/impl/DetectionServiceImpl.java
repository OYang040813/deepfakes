package com.example.springboot.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.Utils.DetectionFactory;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Audio;
import com.example.springboot.entity.Detection;
import com.example.springboot.entity.Image;
import com.example.springboot.entity.Video;
import com.example.springboot.mapper.AudioMapper;
import com.example.springboot.mapper.DetectionMapper;
import com.example.springboot.mapper.ImageMapper;
import com.example.springboot.mapper.VideoMapper;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.service.IDetectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author oy
 */
@Service
public class DetectionServiceImpl extends ServiceImpl<DetectionMapper, Detection> implements IDetectionService {

    @Autowired
    DetectionMapper detectionMapper;
    @Autowired
    DetectionFactory detectionFactory;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    AudioMapper audioMapper;

    private static final String SCRIPTS_FOLDER = "D:\\开发工作\\deepfakes\\FaceForensics\\classification";
    private static final String DOWNLOAD_PATH = System.getProperty("user.dir") + "/OutputFiles/";
    private static final String MODEL_PATH = "D:\\开发工作\\deepfakes\\FaceForensics\\classification\\detect_from_video.py";
    private static final String OUTPUT_FOLDER = "D:\\开发工作\\deepfakes\\FaceForensics\\classification\\output\\";

    @Override
    public Result startDetectionForImage(Integer fileId, Integer pid) {

        Image image = imageMapper.getById(fileId);
        //创建实例
        Detection detection = detectionFactory.create(pid, "图像检测", image.getName(), image.getPath(), image.getLocalpath(), image.getId());
//        Result result = runDetectionLocal(detection.getLocalpath(), MODEL_PATH, OUTPUT_FOLDER);

        return Result.success();
    }

    @Override
    public void download(String flag, String play, HttpServletResponse response) {
        OutputStream os;
        List<String> filenames = FileUtil.listFileNames(DOWNLOAD_PATH);
        String fileName = filenames.stream().filter(name -> name.contains(flag)).findAny().
                orElse("");
        try {
            if (StrUtil.isNotEmpty(fileName)){
                String realName = fileName.substring(fileName.indexOf("_") + 1);
                if("1".equals(play)){
                    response.addHeader("Content-Disposition","inline;filename=" +
                            URLEncoder.encode(realName, "UTF-8"));
                } else {
                    response.addHeader("Content-Disposition","attachment;filename=" +
                            URLEncoder.encode(realName, "UTF-8"));
                }
                byte[] bytes = FileUtil.readBytes(DOWNLOAD_PATH + fileName);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            log.error("文件下载失败",e);
        }
    }

    @Override
    public Result startDetectionForVideo(Integer fileId, Integer pid) {

        Video video = videoMapper.getById(fileId);
        //创建实例
        Detection detection = detectionFactory.create(pid, "视频检测", video.getName(), video.getPath(), video.getLocalpath(), video.getId());
//        Result result = runDetectionLocal(detection.getLocalpath(), MODEL_PATH, OUTPUT_FOLDER);

        return Result.success();
    }

    @Override
    public Result startDetectionForAudioSingle(Integer fileId, Integer pid) {

        Audio audio = audioMapper.getById(fileId);
        //创建实例
        Detection detection = detectionFactory.create(pid, "音频检测", audio.getName(), audio.getPath(), audio.getLocalpath(), audio.getId());
//        Result result = runDetectionLocal(detection.getLocalpath(), MODEL_PATH, OUTPUT_FOLDER);

        return Result.success();
    }

    @Override
    public void startDetectionForAudioDouble(Integer fileId, Integer pid) {
        // to do

    }

    @Override
    public List<Detection> list() {
        return detectionMapper.list();
    }

    @Override
    public boolean save(Detection detection) {

        detection.setUpdatetime(new Date());
        detection.setCreatetime(new Date());

        detectionMapper.save(detection);

        return true;
    }

    @Override
    public Detection getById(Integer id) {
        return detectionMapper.getById(id);
    }

    @Override
    public void update(Detection detection) {
        detection.setUpdatetime(new Date());
        detectionMapper.updateById(detection);
    }

    @Override
    public void deleteById(Integer id) {
        detectionMapper.deleteById(id);
    }

    @Override
    public PageInfo<Detection> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Detection> detections = detectionMapper.listByCondition(baseRequest);
        return new PageInfo<>(detections);
    }

    @Override
    public Result runDetectionCloud(String filePath){
        String url = "在线服务的调用地址";
        String token = "用户Token";

        File file = new File(filePath);
        if (!file.exists()) {
            return Result.error("Error: File not found");
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
                return Result.success(EntityUtils.toString(response.getEntity()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result runDetectionLocal(String inputVideo, String modelPath, String outputFolder) {

        File scriptFile = new File(SCRIPTS_FOLDER, "detect_from_video.py");

        if (!scriptFile.exists()) {
            return Result.error("Python script not found.");
        }

        try {
            List<String> command = new ArrayList<>();
            command.add("python");
            command.add(scriptFile.getAbsolutePath());
            command.add("-i");
            command.add(inputVideo);
            command.add("-m");
            command.add(modelPath);
            command.add("-o");
            command.add(outputFolder);

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(SCRIPTS_FOLDER)); // 设置工作目录
            Process process = pb.start();
            int exitCode = process.waitFor();

            return Result.success(exitCode);
        } catch (IOException | InterruptedException e) {
            return Result.error("Failed to execute script: " + e.getMessage());
        }
    }

    @Override
    public Detection readTxtFile(String filePath, Detection detection) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("File not found or is not a file");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int result = 0;
            String finalResult = null;

            // 读取第一行
            if ((line = br.readLine()) != null) {
                result = Integer.parseInt(line.trim());
            }

            // 读取第二行
            if ((line = br.readLine()) != null) {
                finalResult = line.trim();
            }

            // 处理读取到的数据
            detection.setResult(result);
            update(detection);
            return detection;

            // 在这里你可以将result和finalResult变量保存到类成员变量或其他地方以供后续使用

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading file", e);
        }
    }

    @Override
    public Detection getByFileId(Integer fileId) {
        return detectionMapper.getByFileid(fileId);
    }

    @Override
    public Detection getByName(String name) {
        return detectionMapper.getByName(name);
    }

    @Override
    public Detection getByPath(String path) {
        return detectionMapper.getByPath(path);
    }

}


