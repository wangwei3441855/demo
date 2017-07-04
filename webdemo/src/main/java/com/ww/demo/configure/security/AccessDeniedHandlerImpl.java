package com.ww.demo.configure.security;

import com.ww.demo.configure.BaseController;
import com.ww.demo.configure.RespWriteJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler
{
    private Logger log = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AccessDeniedException e) throws IOException, ServletException
    {
        log.info("[AccessDeniedHandlerImpl.handle] start");

        boolean isAjax = BaseController.isAjaxRequest(httpServletRequest);
        String message = e.getMessage();
        if (!isAjax)
        {
            log.info("[AccessDeniedHandlerImpl.handle] isAjax false");
            String contextPath = httpServletRequest.getContextPath();
            String page403 = contextPath + "/403.html?msg=" + message;
            httpServletResponse.sendRedirect(page403);
        }
        else
        {
            log.info("[AccessDeniedHandlerImpl.handle] isAjax true");
            httpServletResponse.setStatus(403);
            Map<String, String> map = new HashMap<String, String>();
            map.put("msg", message);
            RespWriteJson.writJson(map, httpServletResponse);
        }
        log.info("[AccessDeniedHandlerImpl.handle] end");
    }
}
