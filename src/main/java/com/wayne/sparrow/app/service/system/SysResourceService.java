package com.wayne.sparrow.app.service.system;


import com.wayne.sparrow.app.entity.system.SysResource;

import java.util.List;

/**
 * Created by zhanliquan on 17-8-14.
 * Description:
 */

public interface SysResourceService {
    SysResource findById(Long id);

    List<SysResource> listAll();

    void save(SysResource sysResource);

    List<SysResource> findByIds(Long[] resourceIds);

    void loadResource(boolean reset);
}
