package com.ww.demo.apps.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task
{
    private Logger log = LoggerFactory.getLogger(Task.class);

    public void test()
    {
        log.debug("[Task]------>test");
    }
}
