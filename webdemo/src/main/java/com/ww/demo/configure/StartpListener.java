package com.ww.demo.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
public class StartpListener extends ContextLoaderListener
{

    private static Logger log = LoggerFactory.getLogger(StartpListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        ServletContext context = event.getServletContext();
        String contextPath = context.getContextPath();
        log.info("[StartpListener.contextInitialized] contextPath:{}", contextPath);

        //加载properties配置文件
        log.info("[StartpListener.contextInitialized] load properties start");
        PropertiesUtils.load();
        log.info("[StartpListener.contextInitialized] load properties end");


        log.info("[StartpListener.contextInitialized] contextInitialized complete");

    }
}
