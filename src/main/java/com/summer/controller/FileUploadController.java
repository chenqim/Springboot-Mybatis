package com.summer.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("file")
public class FileUploadController {

    private static Logger logger = Logger.getLogger(FileUploadController.class);

    @RequestMapping("upload")
    // RequestParam要与前端的name相对应
    public String uploadFile (@RequestParam("file") MultipartFile upFile, HttpServletRequest request) {

        if (request.getContentLength() > 0) {
            InputStream inputStream = null;
            FileOutputStream outputStream = null;
            try {
                // 前端上传的文件的流可以从 MultipartHttpServletRequest 中获取
                // MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                // inputStream = multipartHttpServletRequest.getFile("file").getInputStream();
                // 也可以从 MultipartFile 中获取
                inputStream = upFile.getInputStream();
                // 处理文件名称，防止重名，最暴力的方法，直接拼接当前的毫秒数
                String originalName = upFile.getOriginalFilename();
                String name = originalName.substring(0, originalName.lastIndexOf("."));
                String type = originalName.substring(originalName.lastIndexOf("."));
                long now = System.currentTimeMillis();
                File file = new File("D:/", name + "-" + now + type);
                file.createNewFile();

                outputStream = new FileOutputStream(file);

                byte temp[] = new byte[1024];
                int size = -1;
                while ((size = inputStream.read(temp)) != -1) { // 每次读取1KB，直至读完
                    outputStream.write(temp, 0, size);
                }
                logger.info("File load success.");
            } catch (IOException e) {
                logger.warn("File load fail.", e);
            } finally {
                try {
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    logger.warn("File load fail.", e);
                }
            }
        }
        return "Upload Success!";
    }

}
