package com.wayne.sparrow.app.pojo;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhanliquan on 17-8-15.
 * Description:
 */
@Data
public class SysPermission {
    private Long id;
    private SysRole sysRole;
    private SysResource sysResource;
    private Date dateModify;
    private Date dateCreated;
}
