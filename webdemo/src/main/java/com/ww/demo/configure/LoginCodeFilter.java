package com.ww.demo.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangwei on 2017/6/23.
 */
public class LoginCodeFilter implements Filter
{
    private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);

    private static final String isCheckImgCode = PropertiesUtils.get("isCheckImgCode");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        log.debug("[LoginCodeFilter] isCheckImgCode:{}", isCheckImgCode);
        if ("1".equals(isCheckImgCode))
        {
            Object attribute = req.getSession().getAttribute("ImgeCode");
            if (!ObjectUtils.isEmpty(attribute))
            {
                String sessionCode = attribute.toString();
                String code = req.getParameter("code");
                log.debug("[LoginCodeFilter] sessionCode:{},code:{}", sessionCode, code);
                if (!code.equalsIgnoreCase(sessionCode))
                {
                    log.error("[LoginCodeFilter] code error");
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msg", "code error");
                    RespWriteJson.writJson(map, resp);
                }
                else
                {
                    filterChain.doFilter(req, resp);
                }
            }
            else
            {
                log.error("[LoginCodeFilter] code is empty");
                Map<String, String> map = new HashMap<String, String>();
                map.put("msg", "code is empty");
                RespWriteJson.writJson(map, resp);
            }
        }
        else
        {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy()
    {
    }
}
