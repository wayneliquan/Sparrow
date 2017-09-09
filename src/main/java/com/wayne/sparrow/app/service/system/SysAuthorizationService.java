package com.wayne.sparrow.app.service.system;


import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.entity.system.SysUser;
import com.wayne.sparrow.app.pojo.SysPermission;

import java.util.List;

/**
 * Created by zhanliquan on 17-8-10.
 * Description: 授权服务的接口
 */
public interface SysAuthorizationService {
    void authorize(SysUser sysUser, List<SysRole> sysRoleList);

    void authorize(SysRole sysRole, List<SysResource> sysResourceList);

    List<SysPermission> listAllPermission();

    void loadSysPermission(boolean reset);

    List<Long> findResourceIdsByRoleId(Long sysRoleId);
}
