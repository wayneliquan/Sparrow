package com.wayne.sparrow.app.service.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.mapper.system.SysRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-14.
 * Description:
 */
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole findById(Long roleId) {
        return sysRoleMapper.findById(roleId);
    }

    @Override
    public List<SysRole> findByIds(Long[] sysRoleIds) {
        return sysRoleMapper.findByIds(sysRoleIds);
    }

    @Override
    public List<SysRole> listAll() {
        return sysRoleMapper.listAll();
    }

    @Override
    public List<SysResource> listAuthorizedResource(Long sysRoleId) {
        return null;
    }

    @Override
    public void save(SysRole sysRole) {
        if (sysRole.getId() != null) {
            sysRole.setDateModify(new Date());
//            sysRoleMapper.update(sysRole);
        } else {
            sysRole.setDateCreated(new Date());
            sysRoleMapper.insert(sysRole);
        }
    }
}
