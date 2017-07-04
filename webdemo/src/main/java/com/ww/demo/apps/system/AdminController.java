package com.ww.demo.apps.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @RequestMapping("/test")
    @ResponseBody
    public String getImg()
    {
       return "admin.test";
    }
}
