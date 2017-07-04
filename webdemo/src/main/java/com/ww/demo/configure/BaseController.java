package com.ww.demo.configure;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by wangwei on 2017/6/19.
 */
public class BaseController
{
    private static final String UTF_8 = "UTF-8";

    private static ThreadLocal<HttpServletRequest> nativeRequest = new ThreadLocal<HttpServletRequest>();

    private static ThreadLocal<HttpServletResponse> nativeResponse = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequest()
    {
        return nativeRequest.get();
    }

    public static void setNativeRequest(
            HttpServletRequest nativeRequest)
    {
        BaseController.nativeRequest.set(nativeRequest);
    }

    public static HttpServletResponse getResponse()
    {
        return nativeResponse.get();
    }

    public static void setNativeResponse(HttpServletResponse nativeResponse)
    {
        BaseController.nativeResponse.set(nativeResponse);
    }

    @ModelAttribute
    protected final void setRequestAndResponse(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException
    {
        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        setNativeRequest(request);
        setNativeResponse(response);
    }

    public String getLoginErrorMessage()
    {
        Object obj = getRequest().getSession()
                .getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        String msg = null;
        if(!ObjectUtils.isEmpty(obj))
        {
            BadCredentialsException exc = (BadCredentialsException)obj;
            msg = exc.getLocalizedMessage();
        }

        return msg;
    }


    public static boolean isAjaxRequest(HttpServletRequest request)
    {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
