package com.wayne.sparrow.app.mapper.system;

import com.wayne.sparrow.app.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysUserMapper {
    SysUser findById(Long id);
}