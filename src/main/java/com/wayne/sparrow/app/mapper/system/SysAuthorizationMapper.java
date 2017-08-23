package com.wayne.sparrow.app.mapper.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.pojo.SysAuthorization;
import com.wayne.sparrow.app.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhanliquan on 17-8-15.
 * Description:
 */
@Mapper
@Component
public interface SysAuthorizationMapper {

    void insertBathSysAuthorization(List<SysAuthorization> list);

    void insertBathSysPermission(List<SysPermission> list);

    List<SysResource> listCurrentUserResource(@Param("userId") Long userId);

    List<SysRole> listUserRole(@Param("sysUserId") Long sysUserId);

    List<SysPermission> listAllPermission();
}
