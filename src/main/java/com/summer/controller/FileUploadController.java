package com.summer.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public String uploadFile (HttpServletRequest request, HttpServletResponse response) {

        if (request.getContentLength() > 0) {
            InputStream inputStream = null;
            FileOutputStream outputStream = null;
            try {
                inputStream = request.getInputStream();
                // 给新文件拼上时间毫秒，防止重名
                long now = System.currentTimeMillis();
                File file = new File("D:/", "file-" + now + ".txt");
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
