package com.ww.demo.configure.security;

import com.ww.demo.configure.RespWriteJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangwei on 2017/6/21.
 */
public class AuthenticationFailure implements AuthenticationFailureHandler
{
    private Logger log = LoggerFactory.getLogger(AuthenticationFailure.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException
    {
        String errorMsg = e.getMessage().replaceAll(" ", "_");
        log.info("[AuthenticationFailure.onAuthenticationFailure] AuthenticationException：{}",
                errorMsg);
        //重定向回登录页面
       /* String contextPath = httpServletRequest.getContextPath();
        String backlogin = contextPath + "/login.html?error=" + errorMsg;
        httpServletResponse.sendRedirect(backlogin);*/
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg", errorMsg);
        RespWriteJson.writJson(map, httpServletResponse);
    }
}
