package com.wayne.sparrow.app.service.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.entity.system.SysUser;
import com.wayne.sparrow.app.mapper.system.SysAuthorizationMapper;
import com.wayne.sparrow.app.mapper.system.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-11.
 * Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysAuthorizationMapper sysAuthorizationMapper;

    @Override
    public SysUser findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public SysUser findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<SysRole> findRolesBySysUserId(Long sysUserId) {
        return sysAuthorizationMapper.listUserRole(sysUserId);
    }

    @Override
    public List<SysUser> listAll() {
        return userMapper.listAll();
    }

    @Override
    public List<SysResource> listCurrentUserResource() {
        return sysAuthorizationMapper.listCurrentUserResource(1L);
    }

    @Override
    public void save(SysUser sysUser) {
        if (sysUser.getId() != null) {
            userMapper.update(sysUser);
        } else {
            sysUser.setDateCreated(new Date());
            userMapper.insert(sysUser);
        }
    }
}
