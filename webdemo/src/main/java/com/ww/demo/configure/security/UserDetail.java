package com.ww.demo.configure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by wangwei on 2017/6/21.
 */
public class UserDetail implements UserDetails
{
    private String id;

    private String username;

    private String password;

    private boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetail(String id, String username, String password, boolean enabled,
            Collection<? extends GrantedAuthority> authorities)
    {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getId()
    {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return this.enabled;
    }

    @Override
    public boolean equals(Object rhs)
    {
        if (rhs instanceof User)
        {
            return username.equals(((User)rhs).getUsername());
        }
        return false;
    }

    /**
     * Returns the hashcode of the {@code username}.
     */
    @Override
    public int hashCode()
    {
        return username.hashCode();
    }
}
