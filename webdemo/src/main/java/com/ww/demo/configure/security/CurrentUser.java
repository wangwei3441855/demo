package com.ww.demo.configure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentUser
{


    public static Map<String, Object> getCurrentUser()
    {
        UserDetail userDetails = (UserDetail)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> list = new ArrayList<String>();
        for (GrantedAuthority g : authorities)
        {
            list.add(g.getAuthority());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", userDetails.getId());
        map.put("name", userDetails.getUsername());
        map.put("roles", list);
        return map;
    }
}
