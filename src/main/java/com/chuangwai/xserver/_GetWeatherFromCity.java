package com.chuangwai.xserver;

import com.alibaba.fastjson.JSON;
import com.chuangwai.xserver.server.Weather;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xingshi on 15/6/3.
 */
public class _GetWeatherFromCity extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding("utf-8");
        String city = request.getParameter("city");

        try {
            Weather wt = new Weather(city);
            response.getWriter().println(JSON.toJSONString(wt));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
