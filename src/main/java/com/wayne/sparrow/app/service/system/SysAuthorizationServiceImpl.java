package com.wayne.sparrow.app.service.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.entity.system.SysUser;
import com.wayne.sparrow.app.mapper.system.SysAuthorizationMapper;
import com.wayne.sparrow.app.pojo.SysAuthorization;
import com.wayne.sparrow.app.pojo.SysPermission;
import com.wayne.sparrow.app.pojo.SysUserDTO;
import com.wayne.sparrow.core.constants.SysConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-10.
 * Description: 授权服务的实现
 */
@Service
public class SysAuthorizationServiceImpl implements SysAuthorizationService{
    @Autowired
    private SysAuthorizationMapper sysAuthorizationMapper;

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    // 给用户分配角色
    public void authorize(SysUser sysUser, List<SysRole> sysRoleList) {
        List<SysAuthorization> list = new ArrayList<>();
        SysAuthorization sysAuthorization;
        for (SysRole sysRole: sysRoleList){
            sysAuthorization = new SysAuthorization();
            sysAuthorization.setDateCreated(new Date());
            sysAuthorization.setSysUser(sysUser);
            sysAuthorization.setSysRole(sysRole);
            sysAuthorization.setStatus(SysAuthorization.STATUS_NORMAL);
            list.add(sysAuthorization);
        }
        sysAuthorizationMapper.deleteSysAuthorizationBySysRoleId(sysUser.getId());
        sysAuthorizationMapper.insertBathSysAuthorization(list);
        // 如果用户已经登录，更新用户
        // TODO 使用缓存做优化
        List<Object> principalList = sessionRegistry.getAllPrincipals();
        for (Object principal: principalList) {
            if (principal instanceof SysUserDTO) {
                SysUserDTO user = (SysUserDTO) principal;
                String username = user.getUsername();
                if (sysUser.getUsername().equals(username)) {
                    Collection authorities = user.getAuthorities();
                    synchronized (authorities) {
                        // 更新授权, 比较太麻烦了，直接更新吧！
                        authorities.clear();
                        for (SysRole sysRole : sysRoleList) {
                            authorities.add(new SimpleGrantedAuthority(sysRole.getCode()));
                        }
                    }
                }
            }
        }
    }

    // 给角色分配资源
    public void authorize(SysRole sysRole, List<SysResource> sysResourceList) {
        List<SysPermission> list = new ArrayList<>();
        SysPermission sysPermission;
        for (SysResource sysResource: sysResourceList) {
            sysPermission = new SysPermission();
            sysPermission.setSysRole(sysRole);
            sysPermission.setSysResource(sysResource);
            sysPermission.setDateCreated(new Date());
            list.add(sysPermission);
        }
        sysAuthorizationMapper.deletePermissionBySysRoleId(sysRole.getId());
        sysAuthorizationMapper.insertBathSysPermission(list);
    }

    @Override
    public List<SysPermission> listAllPermission() {
        return sysAuthorizationMapper.listAllPermission();
    }

    @Override
    public void loadSysPermission(boolean reset) {
        List<SysPermission> sysPermissionList = sysAuthorizationMapper.listAllPermission();
        SysConstants.setCacheResourceRoleMap(sysPermissionList, reset);
    }

    @Override
    public List<Long> findResourceIdsByRoleId(Long sysRoleId) {
        return sysAuthorizationMapper.findResourceIdsByRoleId(sysRoleId);
    }

    @Override
    public List<Long> findUserGrantedRoleIds(Long sysUserId) {
        return sysAuthorizationMapper.findUserGrantedRoleIds(sysUserId);
    }

}
