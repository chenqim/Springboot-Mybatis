package com.summer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpHelper {

    /**
     * @Description 使用HttpURLConnection发送post请求
     * @params urlParam 请求路径 params 请求参数 charset 字符串编码
     * @Time 2018年08月20日
     */
    public static String sendPost(String urlParam, Map<String, Object> params, String charset) {
        StringBuffer resultBuffer;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> e : params.entrySet()) {
                try {
                    sbParams.append(URLEncoder.encode(e.getKey(), "UTF-8"));
                    sbParams.append("=");
                    sbParams.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
                    sbParams.append("&");
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        // 发送请求
        try {
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            // 设置是否向 httpUrlConnection 输出，因为这个是 post 请求，参数要放在 http 正文内，因此需要设为 true， 默认情况下是 false
            con.setDoOutput(true);
            // 设置是否从 httpUrlConnection 读入，默认情况下是 true
            con.setDoInput(true);
            // 设置请求方式，默认为 get
            con.setRequestMethod("POST");
            // post 请求不能使用缓存
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (sbParams != null && sbParams.length() > 0) {
                // 添加 post 请求参数
                osw = new OutputStreamWriter(con.getOutputStream(), charset);
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                // 如果用于网络传输，记得强制刷新缓冲区，否则输出的数据只停留在缓冲区中，而无法进行网络传输
                osw.flush();
            }
            // 读取返回内容
            resultBuffer = new StringBuffer();
            // 会有ContentLength不存在的情况
            // int contentLength = con.getContentLength();
            // if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            // }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }
            }
        }

        return resultBuffer.toString();
    }

    /**
     * @Description 使用HttpURLConnection发送get请求
     * @params urlParam 请求路径 params 请求参数 charset 字符串编码
     * @Time 2018年08月20日
     */
    public static String sendGet(String urlParam, Map<String, Object> params, String charset) {
        StringBuffer resultBuffer;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
        }
        HttpURLConnection con = null;
        BufferedReader br = null;
        // 发送请求
        try {
            if (sbParams != null && sbParams.length() > 0) {
                urlParam += "?" + sbParams.substring(0, sbParams.length() - 1);
            }
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            // 设置请求方式，默认为 get
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 可以不明文设定连接，conn.getInputStream()会自动连接
            con.connect();

            // 读取返回内容
            resultBuffer = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
            String temp;
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }
            }
        }

        return resultBuffer.toString();
    }

    public static void main(String[] args) {
        HttpHelper hp = new HttpHelper();
        Map<String, Object> map = new HashMap<>();
        map.put("key", "a5c2ad5664975783f09b903a17467c3f");
        map.put("v", "1.0");
        map.put("month", 8);
        map.put("day", 16);
        String s = hp.sendPost("http://api.juheapi.com/japi/toh", map, "UTF-8");
        System.out.println(s);
        String s2 = hp.sendGet("http://api.juheapi.com/japi/toh", map, "UTF-8");
        System.out.println(s2);
    }
}
