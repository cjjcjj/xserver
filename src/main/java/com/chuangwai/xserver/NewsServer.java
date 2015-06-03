package com.chuangwai.xserver;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by xingshi on 15/5/14.
 */
public class NewsServer {
	
	public static void main(String[] args)
	{
		NewsServer.start();
	}
	
    public static void start(){
        Server server = new Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(10012);
        server.setConnectors(new Connector[]{connector});
    
        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ctx.setContextPath("/");
        ctx.addServlet(new ServletHolder(new _GetCategoryServlet()), "/getcategory");
        ctx.addServlet(new ServletHolder(new _GetNewsListServlet()), "/getnewslist");
        ctx.addServlet(new ServletHolder(new _GetNewsServlet()), "/getnews");
        ctx.addServlet(new ServletHolder(new _GetWeatherFromCity()), "/getweather");
        server.setHandler(ctx);
        try{
            server.start();
            server.join();
        }catch (Exception e){ 
            e.printStackTrace();
        }   
    }   
}