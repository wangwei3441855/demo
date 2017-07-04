package com.ww.demo.apps.user.service;

import com.ww.demo.apps.user.domain.Role;
import com.ww.demo.apps.user.domain.UserInfo;
import com.ww.demo.apps.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public boolean addUser(UserInfo user)
    {
        Integer b = userMapper.addUser(user);
        return b.intValue() > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo getUserByName(String userName)
    {
        UserInfo user = userMapper.getUserByName(userName);
        if (!ObjectUtils.isEmpty(user))
        {
            List<Role> userRoles = roleService.getRolesByUserId(user.getUserId());
            user.setRols(userRoles);
        }
        return user;
    }
}
