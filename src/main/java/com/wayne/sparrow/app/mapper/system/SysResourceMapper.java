package com.wayne.sparrow.app.mapper.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceMapper {
    SysResource findById(Long id);

    List<SysResource> findByIds(@Param("resourceIds") Long[] resourceIds);

    void insert(SysResource sysResource);

    List<SysResource> listAll();

    void update(SysResource sysResource);
}