package com.wayne.sparrow.app.service.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.entity.system.SysUser;
import com.wayne.sparrow.app.mapper.system.SysAuthorizationMapper;
import com.wayne.sparrow.app.pojo.SysAuthorization;
import com.wayne.sparrow.app.pojo.SysPermission;
import com.wayne.sparrow.core.constants.SysConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        sysAuthorizationMapper.insertBathSysAuthorization(list);
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

}
