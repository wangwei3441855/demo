package com.ww.demo.apps.user.mapper;

import com.ww.demo.apps.user.domain.ResourcesInfo;
import com.ww.demo.apps.user.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper
{

    List<Role> getRolesByUserId(String userId);

    List<Role> getAllRoles();

    List<ResourcesInfo> getResource(Map<String, String> map);
}
