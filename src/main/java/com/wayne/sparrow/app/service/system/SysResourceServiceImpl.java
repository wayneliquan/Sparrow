package com.wayne.sparrow.app.service.system;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.mapper.system.SysResourceMapper;
import com.wayne.sparrow.core.constants.SysConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-14.
 * Description:
 */

@Slf4j
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;


    @Override
    public SysResource findById(Long id) {
        return sysResourceMapper.findById(id);
    }

    @Override
    public List<SysResource> listAll() {
        return sysResourceMapper.listAll();
    }

    @Override
    public void save(SysResource sysResource) {
        if (sysResource.getId() != null) {
            sysResource.setDateModify(new Date());
//            sysResourceMapper.update(sysResource);
        } else {
            if (sysResource.getPid() == null) {
                sysResource.setPid(0L);
            }
            if (sysResource.getLevel() == null) {
                sysResource.setLevel(getLevelByPid(sysResource.getPid()));
            }
            sysResource.setDateCreated(new Date());
            sysResourceMapper.insert(sysResource);
        }
    }

    @Override
    public List<SysResource> findByIds(Long[] resourceIds) {
        return sysResourceMapper.findByIds(resourceIds);
    }

    @Override
    public void loadResource(boolean reset) {
        try {
            List<SysResource> sysResourceList = listAll();
            SysConstants.setCacheResource(sysResourceList, reset);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer getLevelByPid(Long pid) {
        if (pid == 0) {
            return 1;
        } else {
            SysResource resource = sysResourceMapper.findById(pid);
            return resource.getLevel() + 1;
        }
    }
}
