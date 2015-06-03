package com.chuangwai.xserver;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class _GetNewsServlet extends HttpServlet {
	
	private static Mysql mysql = new Mysql("jdbc:mysql://localhost/chuangwai?useUnicode=true&characterEncoding=utf8","root","chuangwai123");

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding("utf-8");
        String a = request.getParameter("id");
        
		ResultSet ret ;
		ret = mysql.query("select id,category,title,content,source1,source2,pub_time from news where id = "+a+";");
	    System.out.println("2222222222");
		try {
    		News tmp = new News();
	    	while( ret.next() )
	    	{
	    		tmp.setId( ret.getInt(1));
	    		tmp.setCategory( ret.getString(2));
	    		tmp.setTitle( ret.getString(3));
	    		tmp.setContent( ret.getString(4));
	    		tmp.setSource1( ret.getString(5));
	    		tmp.setSource2( ret.getString(6));
	    		tmp.setPubtime( ret.getInt(7));
	    	}
	    	response.getWriter().println(JSON.toJSONString(tmp));
        }catch (Exception e){ 
            e.printStackTrace();
        }   
    } 
}
