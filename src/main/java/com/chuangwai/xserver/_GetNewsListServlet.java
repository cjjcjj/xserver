package com.chuangwai.xserver;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class _GetNewsListServlet extends HttpServlet {
	
	private static Mysql mysql = new Mysql("jdbc:mysql://localhost/chuangwai?useUnicode=true&characterEncoding=utf8","root","chuangwai123");

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding("utf-8");
        String a = request.getParameter("cat");
        String b = request.getParameter("num");
        System.out.println(a+"---"+b);
        
		ResultSet ret ;
		ret = mysql.query("select id,category,title,source1,source2,pub_time from news where category=\""+a+"\" order by pub_time desc limit "+b+";");
	    System.out.println("2222222222");
		try {
	    	ArrayList<News> ans = new ArrayList<News>() ;
	    	while( ret.next() )
	    	{
	    		News tmp = new News();
	    		tmp.setId( ret.getInt(1));
	    		tmp.setCategory( ret.getString(2));
	    		tmp.setTitle( ret.getString(3));
	    		tmp.setSource1( ret.getString(4));
	    		tmp.setSource2( ret.getString(5));
	    		tmp.setPubtime( ret.getInt(6));
	    		ans.add(tmp);
	    	}
	    	System.out.println(ans);
            response.getWriter().println(JSON.toJSONString(ans));
        }catch (Exception e){ 
            e.printStackTrace();
        }   
    } 

}
