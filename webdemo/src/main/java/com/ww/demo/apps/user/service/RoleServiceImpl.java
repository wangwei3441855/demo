package com.ww.demo.apps.user.service;

import com.ww.demo.apps.user.domain.ResourcesInfo;
import com.ww.demo.apps.user.domain.Role;
import com.ww.demo.apps.user.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUserId(String userId)
    {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public List<Role> getAllRoles()
    {
        return roleMapper.getAllRoles();
    }

    @Override
    public List<ResourcesInfo> getResource(Map<String, String> map)
    {
        return roleMapper.getResource(map);
    }
}
