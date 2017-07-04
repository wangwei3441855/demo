package com.ww.demo.configure.security;

import com.ww.demo.apps.user.domain.Role;
import com.ww.demo.apps.user.domain.UserInfo;
import com.ww.demo.apps.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        log.info("[UserDetailsServiceImpl.loadUserByUsername] start");
        UserInfo userInfo = userService.getUserByName(userName);
        if (userInfo == null)
        {
            log.error("[UserDetailsServiceImpl.loadUserByUsername] username not find User");
            throw new UsernameNotFoundException("username not find User");
        }

        List<Role> rols = userInfo.getRols();
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for (int i = 0; i < rols.size(); i++)
        {
            String roleName = rols.get(i).getRoleName();
            GrantedAuthority ga = new SimpleGrantedAuthority(roleName);
            list.add(ga);

        }
        UserDetail user = new UserDetail(userInfo.getUserId(),
                userInfo.getUserName(),
                userInfo.getPassword(),
                true,
                list);
        log.info("[UserDetailsServiceImpl.loadUserByUsername] end");
        return user;
    }
}
