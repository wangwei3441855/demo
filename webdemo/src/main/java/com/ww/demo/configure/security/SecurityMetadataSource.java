package com.ww.demo.configure.security;

import com.ww.demo.apps.user.domain.ResourcesInfo;
import com.ww.demo.apps.user.domain.Role;
import com.ww.demo.apps.user.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{

    private Logger log = LoggerFactory.getLogger(AuthenticationFailure.class);

    @Autowired
    private RoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException
    {
        log.info("[SecurityMetadataSource.getAttributes] start");
        HttpServletRequest request = ((FilterInvocation)o).getRequest();
        // String requestUrl = request.getRequestURL().toString();
        Map<String, Collection<ConfigAttribute>> resource = getResource();
        Collection<ConfigAttribute> confAttr = new ArrayList<ConfigAttribute>();
        for (String key : resource.keySet())
        {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(key);
            if (requestMatcher.matches(request))
            {
                Collection<ConfigAttribute> configAttributes = resource.get(key);
                confAttr.addAll(configAttributes);
                // return resource.get(key);
            }
        }
        if (confAttr != null && confAttr.size() != 0)
        {
            return confAttr;
        }

        log.info("[SecurityMetadataSource.getAttributes] end");
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        log.info("[SecurityMetadataSource.getAllConfigAttributes] start");
        Collection<ConfigAttribute> allConfig = this.getAllConfig();
        log.info("[SecurityMetadataSource.getAllConfigAttributes] end");
        return allConfig;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return true;
    }

    private Map<String, Collection<ConfigAttribute>> getResource()
    {
        Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Map<String, String> emptyMap = new HashMap<String, String>();
        List<ResourcesInfo> resource = roleService.getResource(emptyMap);
        if (resource != null && resource.size() != 0)
        {
            for (int i = 0; i < resource.size(); i++)
            {
                ResourcesInfo ri = resource.get(i);
                String resourceContent = ri.getResourceContent();
                String roleName = ri.getRoleName();

                if (resourceMap.containsKey(resourceContent))
                {
                    Collection<ConfigAttribute> configAttributes = resourceMap.get(resourceContent);
                    SecurityConfig securityConfig = new SecurityConfig(roleName);
                    configAttributes.add(securityConfig);
                    resourceMap.put(resourceContent, configAttributes);
                }
                else
                {
                    Collection<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
                    SecurityConfig securityConfig = new SecurityConfig(roleName);
                    configAttributes.add(securityConfig);
                    resourceMap.put(resourceContent, configAttributes);
                }
            }
        }
        return resourceMap;
    }

    private Collection<ConfigAttribute> getAllConfig()
    {
        Collection<ConfigAttribute> allAttribute = new HashSet<ConfigAttribute>();
        log.info("[SecurityMetadataSource.getAllConfig] query getAllRoles");
        List<Role> allRoles = roleService.getAllRoles();
        if (allRoles != null && allRoles.size() != 0)
        {
            for (int i = 0; i < allRoles.size(); i++)
            {
                Role role = allRoles.get(i);
                SecurityConfig attrConfig = new SecurityConfig(role.getRoleName());
                log.info("[SecurityMetadataSource.getAllConfig] add ConfigAttribute:{}", role.getRoleName());
                allAttribute.add(attrConfig);
            }
        }
        return allAttribute;
    }
}
