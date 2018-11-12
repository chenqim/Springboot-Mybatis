package com.summer.util;
import com.summer.model.AccessToken;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Configuration
@EnableConfigurationProperties(AccessToken.class)
public class ImageToText {

    @Autowired
    private AccessToken accessToken;

    public String getBaiduAccessToken() {

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

    public StringBuffer imageToText(FileInputStream fis) {

        StringBuffer sb = new StringBuffer();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
        try {
            // FileInputStream fis = new FileInputStream(new File(filePath));
            // 将图片转换成byte数组
            byte[] imgData = new byte[fis.available()];
            fis.read(imgData);
            // 将图片进行Base64编码
            Base64.Encoder encoder = Base64.getEncoder();
            String imgStr = encoder.encodeToString(imgData);
            // 将请求参数放入body 请求参数进行URLEncoder
            Map<String, Object> map = new HashMap<>();
            map.put("image", imgStr);
            String accessTokenStr = accessToken.getAccessToken();
            String result = HttpHelper.sendPost(url + "?access_token=" + accessTokenStr, map, "UTF-8");
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

}
