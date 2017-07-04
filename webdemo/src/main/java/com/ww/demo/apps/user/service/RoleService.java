package com.ww.demo.apps.user.service;

import com.ww.demo.apps.user.domain.ResourcesInfo;
import com.ww.demo.apps.user.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2017/6/21.
 */
public interface RoleService
{
    List<Role> getRolesByUserId(String userId);

    List<Role> getAllRoles();

    List<ResourcesInfo> getResource(Map<String, String> map);
}
