package com.ww.demo.configure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by wangwei on 2017/6/23.
 */
public class RespWriteJson
{
    private static Logger log = LoggerFactory.getLogger(RespWriteJson.class);

    public static void writJson(Object obj, HttpServletResponse resp)
    {
        OutputStream outputStream = null;
        OutputStreamWriter ow = null;
        try
        {
            outputStream = resp.getOutputStream();
            ow = new OutputStreamWriter(outputStream, "UTF-8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", obj);
            JSON.writeJSONString(ow, obj);
        }
        catch (IOException e)
        {
            log.info("[RespWritJson.writJson] IOException：{}", e);
        }
        finally
        {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(ow);
        }
    }

}
