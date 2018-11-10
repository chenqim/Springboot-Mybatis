package com.summer.util;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ImageToText {

    public static String getBaiduAccessToken() {

        String url = "https://aip.baidubce.com/oauth/2.0/token";
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("client_id", "bHbGO02sEitZq9pDUNNiQ0Ql");
        map.put("client_secret", "9NDfmBsRMYKqmgXwUKEOjRwGnHH5gVls");
        String result = HttpHelper.sendGet(url, map, "UTF-8");
        JSONObject jsonObject = JSONObject.fromObject(result);
        String access_token = jsonObject.getString("access_token");
        return access_token;

    }

    public static StringBuffer imageToText(String filePath) {

        StringBuffer sb = new StringBuffer();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
        try {
            // 将图片转换成byte数组
            FileInputStream fis = new FileInputStream(new File(filePath));
            byte[] imgData = new byte[fis.available()];
            fis.read(imgData);
            // 将图片进行Base64编码
            Base64.Encoder encoder = Base64.getEncoder();
            String imgStr = encoder.encodeToString(imgData);
            // 将请求参数放入body 请求参数进行URLEncoder
            Map<String, Object> map = new HashMap<>();
            map.put("image", imgStr);
            // 读取properties配置文件暂时不成功
            /*Properties properties = new Properties();
            FileInputStream in = new FileInputStream("com/summer/util/accessToken.properties");
            properties.load(in);
            in.close();
            String accessToken = properties.getProperty("access_token");*/
            String accessToken = "24.a543e6858cb39b765f21a75977f7b8e7.2592000.1544343065.282335-14740762";
            String result = HttpHelper.sendPost(url + "?access_token=" + accessToken, map, "UTF-8");
            JSONObject jsonObject = JSONObject.fromObject(result);
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonObject.get("words_result");

            for (Map<String, Object> m : list) {
                sb.append(m.get("words"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    public static void main(String[] args) {

        // System.out.println(getBaiduAccessToken());
        // 本地图片路径
        String filePath = "C:\\Users\\xm\\Desktop\\宁波2.jpg";
        StringBuffer text = imageToText(filePath);
        System.out.println(text);
    }

}
