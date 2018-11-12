package com.summer.controller;

import com.summer.util.ImageToText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ImageToText imageToText;

    @RequestMapping("/getAccessToken")
    public String getAccessToken () {
        String accessToken = imageToText.getBaiduAccessToken();
        return accessToken;
    }

}
