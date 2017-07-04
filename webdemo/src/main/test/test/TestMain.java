package test;

import com.ww.demo.utils.http.HttpClientTools;

import java.util.HashMap;
import java.util.Map;

//对接口进行测试
public class TestMain
{
    private String url = "https://www.baidu.com";

    private String charset = "utf-8";

    private HttpClientTools httpClientUtil = null;

    public TestMain()
    {
        httpClientUtil = new HttpClientTools();
    }

    public void test()
    {
        String httpOrgCreateTest = url;
        Map<String, String> createMap = new HashMap<String, String>();
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest, createMap, charset);
        System.out.println("result:" + httpOrgCreateTestRtn);
    }

    public static void main(String[] args)
    {
        TestMain main = new TestMain();
        main.test();
    }
}
