package com.ww.demo.apps.user.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwei on 2017/6/19.
 */
public class UserInfo
{
    private String userId;

    private String userName;

    private String password;

    private int status;

    private String descn;

    private List<Role> rols = new ArrayList<Role>();

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getDescn()
    {
        return descn;
    }

    public void setDescn(String descn)
    {
        this.descn = descn;
    }

    public List<Role> getRols()
    {
        return rols;
    }

    public void setRols(List<Role> rols)
    {
        this.rols = rols;
    }
}
