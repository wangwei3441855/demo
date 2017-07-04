package com.ww.demo.apps.user.domain;

import java.util.ArrayList;
import java.util.List;

public class Role
{
    private String roleId;

    private String roleName;

    private String desc;

    private List<ResourcesInfo> list = new ArrayList<ResourcesInfo>();

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public List<ResourcesInfo> getList()
    {
        return list;
    }

    public void setList(List<ResourcesInfo> list)
    {
        this.list = list;
    }
}
