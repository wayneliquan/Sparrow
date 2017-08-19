package com.wayne.sparrow.app.configuration.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-10.
 * Description:
 */
@Component
public class MyFilterSecurityInterceptor extends FilterSecurityInterceptor {

    @Override
    public void afterPropertiesSet() throws Exception {
        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        voters.add(new RoleVoter());
        voters.add(new AuthenticatedVoter());
        setAccessDecisionManager(new AffirmativeBased(voters));
        setSecurityMetadataSource(new MySecurityMetadataSource());
        super.afterPropertiesSet();
    }
}
