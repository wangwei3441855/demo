package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangwei on 2017/6/23.
 */
public class LogTest
{
    private static Logger log = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args)
    {
        log.debug("[LogTest] testLog-->debug");
        log.info("[LogTest] testLog-->info");
        log.warn("[LogTest] testLog-->warn");
        log.error("[LogTest] testLog-->error");
    }
}
