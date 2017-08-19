package com.wayne.sparrow.app.service.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;

import java.util.List;

/**
 * Created by zhanliquan on 17-8-14.
 * Description:
 */
public interface SysRoleService {
    SysRole findById(Long roleId);

    List<SysRole> findByIds(Long[] sysRoleIds);

    List<SysRole> listAll();

    List<SysResource> listAuthorizedResource(Long sysRoleId);

    void save(SysRole sysRole);
}
