package com.wayne.sparrow.app.configuration.security;

import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.entity.system.SysUser;
import com.wayne.sparrow.app.service.system.SysUserService;
import com.wayne.sparrow.core.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-9.
 * Description: 配置SpringSecurity的用户认证部分，就是提供用户登录，用户有什么角色（权限）
 */
@Slf4j
public class LoginUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername : " + username);
        SysUserService sysUserService = SpringContextUtil.getBean(SysUserService.class);

        SysUser sysUser = sysUserService.findByUsername(username);
        if (sysUser == null) {
            log.info("没有对应的用户！");
            throw new UsernameNotFoundException("没有对应的用户！");
        }
        List<SysRole> sysRoleList = sysUserService.findRolesBySysUserId(sysUser.getId());
        if (ListUtils.isEmpty(sysRoleList)) {
            log.info("没有分配对应的权限");
            throw new UsernameNotFoundException("没有分配对应的权限");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (SysRole sysRole : sysRoleList) {
            GrantedAuthority authority = new SimpleGrantedAuthority(sysRole.getCode());
            grantedAuthorities.add(authority);
        }
        return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
    }
}