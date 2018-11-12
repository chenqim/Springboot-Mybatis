package com.summer.controller;

import com.summer.util.ImageToText;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@RequestMapping("/imageToText")
public class BaiduImageToTextController {

    private static Logger logger = Logger.getLogger(BaiduImageToTextController.class);

    @Autowired
    private ImageToText imageToText;

    @RequestMapping("/getAccessToken")
    public String getAccessToken () {
        String accessToken = imageToText.getBaiduAccessToken();
        return accessToken;
    }

    @RequestMapping("/upload")
    // RequestParam要与前端的name相对应
    public String uploadFile (@RequestParam("file") MultipartFile upFile, HttpServletRequest request) {

        StringBuffer sb = null;
        if (request.getContentLength() > 0) {
            InputStream inputStream = null;
            try {
                inputStream = upFile.getInputStream();
                sb = imageToText.imageToText((FileInputStream) inputStream);
                logger.info("File load success.");
            } catch (IOException e) {
                logger.warn("File load fail.", e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.warn("File load fail.", e);
                }
            }
        }
        return sb.toString();
    }

}
