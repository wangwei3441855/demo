package com.ww.demo.utils.file;

import com.ww.demo.configure.BaseController;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public abstract class FileTools
{
    private static final Logger logger = LoggerFactory.getLogger(FileTools.class);

    public static void zipProject(File inputFile, File zipFileName) throws Exception
    {
        FileOutputStream out = null;
        ZipOutputStream zOut = null;
        try
        {
            out = new FileOutputStream(zipFileName);
            zOut = new ZipOutputStream(out);
            if (inputFile.isDirectory())
            {
                zipDir(inputFile, zOut, null);
            }
            else
            {
                zipFile(inputFile, zOut, null);
            }
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
        finally
        {
            if (zOut != null)
            {
                IOUtils.closeQuietly(zOut);
            }
            if (out != null)
            {
                IOUtils.closeQuietly(out);
            }
        }
    }

    private static void zipDir(File inputFile, ZipOutputStream zOut,
            String parent) throws IOException
    {
        String base = "";
        if (parent != null)
        {
            base = parent + inputFile.getName() + "/";
        }
        File[] files = inputFile.listFiles();
        if (files != null && files.length != 0)
        {
            for (File f : files)
            {
                if (f.isDirectory())
                {
                    zipDir(f, zOut, base);
                }
                else
                {
                    zipFile(f, zOut, base);
                }
            }
        }
    }

    private static void zipFile(File f, ZipOutputStream zOut, String dir)
            throws IOException
    {

        zOut.putNextEntry(new ZipEntry(dir + f.getName()));
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(f);
            int len;
            byte[] bytes = new byte[Integer.valueOf("1024")];
            while ((len = in.read(bytes, 0, Integer.valueOf("1024"))) != -1)
            {
                zOut.write(bytes, 0, len);
            }
        }
        catch (IOException e)
        {
            throw new IOException("zipFile IOException");
        }
        finally
        {
            if (in != null)
            {
                IOUtils.closeQuietly(in);
            }
        }

    }

    public static void setFile700Role(File file)
    {
        //先取消所有权限
        file.setReadable(false, false);
        file.setWritable(false, false);
        file.setExecutable(false, false);
        //开启创建者的读权限
        file.setReadable(true, true);
        file.setWritable(true, true);
        file.setExecutable(true, true);
    }

    public static void setFile400Role(File file)
    {
        //先取消所有权限
        file.setReadable(false, false);
        file.setWritable(false, false);
        file.setExecutable(false, false);
        //开启创建者的读权限
        file.setReadable(true, true);
    }

    public static void setFile600Role(File file)
    {
        //先取消所有权限
        file.setReadable(false, false);
        file.setWritable(false, false);
        file.setExecutable(false, false);

        file.setReadable(true, true);
        file.setWritable(true, true);
    }

    public static void download(String downFilePath, String downName)
            throws IOException
    {
        InputStream in = null;
        OutputStream out = null;
        try
        {
            HttpServletResponse response = BaseController.getResponse();
            File downFile = new File(downFilePath);
            in = new FileInputStream(downFile);
            downName = URLEncoder.encode(downName, "UTF-8");
            downName = downName.replace("+", "%20");

            String contentDisposition = "attachment; filename*=UTF-8\'\'" + downName;
            response.setHeader("Content-Disposition", contentDisposition);
            response.setContentType("application/x-download");

            out = response.getOutputStream();
            IOUtils.copy(in, out);
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (null != out)
            {
                IOUtils.closeQuietly(out);
            }
            if (null != in)
            {
                IOUtils.closeQuietly(in);
            }
        }
    }
}
