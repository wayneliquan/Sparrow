package com.wayne.sparrow.app.configuration.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;

/**
 * Created by zhanliquan on 17-8-10.
 * Description: 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色访问
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    /**
     * 就是 ConfigAttribute 就是需要的角色（权限）
     * @param object 包含了真正访问的资源，
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation)object;
        filterInvocation.getRequestUrl();
        RequestMatcher matcher = new AntPathRequestMatcher("/test/home");
        if (matcher.matches(filterInvocation.getHttpRequest())) {
            Collection<ConfigAttribute> attributes = SecurityConfig.createList("ROLE_USER");
            return attributes;
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
