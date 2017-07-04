package com.ww.demo.configure;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtils
{

    private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);

    private static Map<String, String> map = new HashMap<String, String>();

    public static void load()
    {
        ClassPathResource classpath = new ClassPathResource("/properties");
        try
        {
            File file = classpath.getFile();
            File[] files = file.listFiles();
            if (files != null && files.length != 0)
            {
                for (File f : files)
                {
                    loadProperty(f);
                }
            }
        }
        catch (IOException e)
        {
            log.info("[IOException] [PropertiesUtils.load]:{}", e.getMessage());
        }
    }

    private static void loadProperty(File file)
    {
        log.info("[PropertiesUtils.loading]:{}", file.getName());
        Properties prop = new Properties();
        InputStream is = null;
        try
        {
            is = new FileInputStream(file);
            prop.load(is);
            Set<String> keys = prop.stringPropertyNames();
            for (String key : keys)
            {
                map.put(key, prop.getProperty(key));
            }
        }
        catch (IOException e)
        {
            log.error("[IOException] [PropertiesUtils.loadProperty]:{}", e.getMessage());
        }
        catch (Exception e)
        {
            log.error("[Exception] [PropertiesUtils.loadProperty]:{}", e.getMessage());
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    public static String get(String key)
    {
        return map.get(key);
    }
}
