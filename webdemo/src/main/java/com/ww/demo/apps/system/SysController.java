package com.ww.demo.apps.system;

import com.ww.demo.apps.system.service.SysService;
import com.ww.demo.configure.BaseController;
import com.ww.demo.configure.CreateImageCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/sys")
public class SysController extends BaseController
{

    private Logger log = LoggerFactory.getLogger(SysController.class);

    @Autowired
    private SysService sysService;

    @RequestMapping("/getImg")
    public void getImg()
    {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        HttpSession session = request.getSession();
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        CreateImageCode vCode = new CreateImageCode();
        session.setAttribute("ImgeCode", vCode.getCode());
        try
        {
            vCode.write(response.getOutputStream());
        }
        catch (IOException e)
        {
            log.error("[SysController.getImg] IOException:{}",e.getMessage());
        }
    }
}
