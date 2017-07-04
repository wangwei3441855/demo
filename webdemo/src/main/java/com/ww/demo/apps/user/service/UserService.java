package com.ww.demo.apps.user.service;

import com.ww.demo.apps.user.domain.UserInfo;

/**
 * Created by wangwei on 2017/6/19.
 */
public interface UserService
{
    boolean addUser(UserInfo user);

    UserInfo getUserByName(String userName);
}
