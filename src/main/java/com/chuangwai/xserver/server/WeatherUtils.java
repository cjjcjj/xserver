package com.chuangwai.xserver.server;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by xingshi on 15/6/3.
 */
public class WeatherUtils {
    public static JSONObject getWeatherJson(String city){
        try {
            URL url = new URL("http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=json&ak=UiNwukklKmIGjQG6CjCoj5rf");
            URLConnection connectionData = url.openConnection();
            connectionData.setConnectTimeout(1000);
            StringBuilder sb = null;
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        connectionData.getInputStream(), "UTF-8"));
                sb = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null)
                    sb.append(line);
            } catch (SocketTimeoutException e) {
                System.out.println("连接超时");
            } catch (FileNotFoundException e) {
                System.out.println("加载文件出错");
            }
            String datas = sb.toString();
            JSONObject jsonData = JSONObject.parseObject(datas);
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
