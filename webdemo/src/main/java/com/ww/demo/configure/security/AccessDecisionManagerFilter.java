package com.ww.demo.configure.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public class AccessDecisionManagerFilter implements AccessDecisionManager
{

    private Logger log = LoggerFactory.getLogger(AccessDecisionManagerFilter.class);

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException
    {
        log.info("[AccessDecisionManagerFilter.decide] start");
        if (null == collection)
        {
            return;
        }
        Iterator<ConfigAttribute> cons = collection.iterator();
        while (cons.hasNext())
        {
            ConfigAttribute ca = cons.next();
            String needRole = ((SecurityConfig)ca).getAttribute();
            for (GrantedAuthority gra : authentication.getAuthorities())
            {
                if (needRole.trim().equals(gra.getAuthority().trim()))
                {
                    return;
                }
            }
        }
        log.info("[AccessDecisionManagerFilter.decide] end");
        throw new AccessDeniedException("Access Denied ");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute)
    {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return true;
    }
}
