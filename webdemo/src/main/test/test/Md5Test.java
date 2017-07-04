package test;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Created by wangwei on 2017/6/22.
 */
public class Md5Test
{
    public static void main(String[] args)
    {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        String s = md5.encodePassword("wangwei", null);
        System.out.println(s);
    }
}
