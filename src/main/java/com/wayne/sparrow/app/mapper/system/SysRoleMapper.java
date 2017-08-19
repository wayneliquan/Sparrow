package com.wayne.sparrow.app.mapper.system;

import com.wayne.sparrow.app.entity.system.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    SysRole findById(Long id);

    List<SysRole> findByIds(@Param("ids") Long[] ids);

    void insert(SysRole sysRole);

    List<SysRole> listAll();

    void update(SysRole sysRole);
}