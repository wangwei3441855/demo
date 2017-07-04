package com.ww.demo.configure.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class AuthenticationManagerFilter implements AuthenticationManager
{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        return null;
    }
}
