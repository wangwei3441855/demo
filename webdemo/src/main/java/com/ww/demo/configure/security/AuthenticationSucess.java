package com.ww.demo.configure.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSucess implements AuthenticationSuccessHandler
{

    private Logger log = LoggerFactory.getLogger(AuthenticationSucess.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException
    {
        log.info("[AuthenticationSucess.onAuthenticationSuccess] {}", authentication.getName());
        String contextPath = httpServletRequest.getContextPath();
        String sendPage = contextPath + "/index.html";
        httpServletResponse.sendRedirect(sendPage);
    }
}
