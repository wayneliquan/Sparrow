package com.wayne.sparrow.app.pojo;


import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.entity.system.SysUser;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhanliquan on 17-8-15.
 * Description:
 */
@Data
public class SysAuthorization {
    public static final int STATUS_NORMAL = 0;
    private Long id;
    private SysUser sysUser;
    private SysRole sysRole;
    private Integer status;
    private Date dateModify;
    private Date dateCreated;
}
