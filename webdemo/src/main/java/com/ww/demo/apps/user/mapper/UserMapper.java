package com.ww.demo.apps.user.mapper;

import com.ww.demo.apps.user.domain.UserInfo;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper
{
    Integer addUser(UserInfo user);

    UserInfo getUserByName(String userName);
}
