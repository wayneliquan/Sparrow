package com.wayne.sparrow.app.pojo;

import com.wayne.sparrow.app.entity.system.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by zhanliquan on 17-8-29.
 * Description:
 */
@Data
public class SysUserDTO implements UserDetails {
    private final SysUser sysUser;
    private Collection<GrantedAuthority> authorities;

    public SysUserDTO(SysUser sysUser, Collection<GrantedAuthority> authorities) {
        this.sysUser = sysUser;
        this.authorities = authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
