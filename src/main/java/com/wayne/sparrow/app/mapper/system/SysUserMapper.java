package com.wayne.sparrow.app.mapper.system;

import com.wayne.sparrow.app.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserMapper {
    SysUser findById(Long id);

    SysUser findByUsername(String username);

    void insert(SysUser sysUser);

    List<SysUser> listAll();

    void update(SysUser sysUser);
}