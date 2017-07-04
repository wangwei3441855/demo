package com.ww.demo.apps.test;

import com.alibaba.fastjson.JSONObject;
import com.ww.demo.apps.test.domain.Test;
import com.ww.demo.apps.test.service.TestService;
import com.ww.demo.configure.PropertiesUtils;
import com.ww.demo.configure.security.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController
{
    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping("/testLog")
    @ResponseBody
    public Map<String, Object> testLog()
    {
        log.debug("[TestController] testLog-->debug");
        log.info("[TestController] testLog-->info");
        log.warn("[TestController] testLog-->warn");
        log.error("[TestController] testLog-->error");

        JSONObject object = new JSONObject();
        object.put("data", "呵呵呵");
        object.put("str", null);
        return object;
    }

    @RequestMapping("/getList")
    @ResponseBody
    public List<Test> getList()
    {
        log.debug("[TestController] get-->debug");
        return testService.getList();
    }

    @RequestMapping("/getProperties")
    @ResponseBody
    public String getProperties()
    {
        log.debug("[TestController] getProperties-->debug");
        String s = PropertiesUtils.get("test");
        String s1 = PropertiesUtils.get("test2");
        return s + ":" + s1;
    }

    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public Map<String, Object> getCurrentUser()
    {
        return CurrentUser.getCurrentUser();
    }
}
