package com.wayne.sparrow.app.service.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.entity.system.SysUser;

import java.util.List;

/**
 * Created by zhanliquan on 17-8-11.
 * Description:
 */
public interface SysUserService {
    SysUser findById(Long id);

    SysUser findByUsername(String username);

    List<SysRole> findRolesBySysUserId(Long sysUserId);

    List<SysUser> listAll();

    List<SysResource> listCurrentUserResource();

    void save(SysUser sysUser);
}
