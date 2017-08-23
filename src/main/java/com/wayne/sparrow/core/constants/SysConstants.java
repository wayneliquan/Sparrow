package com.wayne.sparrow.core.constants;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.pojo.SysPermission;
import com.wayne.sparrow.app.service.system.SysAuthorizationService;
import com.wayne.sparrow.app.service.system.SysResourceService;
import com.wayne.sparrow.core.util.SpringContextUtil;
import com.wayne.sparrow.core.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanliquan on 17-8-21.
 * Description:
 */
@Slf4j
public class SysConstants {
    // resource_id --> SysResource
    public static Map<Long, SysResource> SYS_RESOURCE_MAP = new HashMap<>();
    public static Map<String, List<String>> SYS_RESOURCE_ROLE_MAP = new HashMap<>();
    public static List<AntPathRequestMatcher> SYS_URL_MATCHER_LIST = new ArrayList<>();

    public void init() {
        loadResource();
        loadResourceRoleMap();
    }

    private void loadResourceRoleMap() {
        try {
            SysAuthorizationService sysAuthorizationService = SpringContextUtil.getBean(SysAuthorizationService.class);
            sysAuthorizationService.loadSysPermission(false);
            log.info("加载系统权限缓存成功！");
        } catch (Exception e) {
            log.error("加载系统权限缓存错误", e);
        }
    }

    private void loadResource() {
        try {
            SysResourceService sysResourceService = SpringContextUtil.getBean(SysResourceService.class);
            sysResourceService.loadResource(false);
            log.info("加载系统资源缓存成功！");
        } catch (Exception e) {
            log.error("加载系统资源缓存错误", e);
        }
    }

    /**
     * 设置系统资源的缓存或更新缓存
     * @param sysResourceList
     * @param reset
     */
    public static void setCacheResource(List<SysResource> sysResourceList, boolean reset) {
        if(reset){
            SYS_RESOURCE_MAP = new HashMap<>();
            SYS_URL_MATCHER_LIST = new ArrayList<>();
        }
        if(Validator.isNotNull(sysResourceList)){
            for(SysResource res:sysResourceList){
                SYS_RESOURCE_MAP.put(res.getId(), res);
                SYS_URL_MATCHER_LIST.add(new AntPathRequestMatcher(res.getUrl()));
            }
        }
    }

    public static void setCacheResourceRoleMap(List<SysPermission> sysPermissionList, boolean reset) {
        if (reset) {
            SYS_RESOURCE_ROLE_MAP = new HashMap<>();
        }
        if (Validator.isNotNull(sysPermissionList)) {
            for(SysPermission sysPermission: sysPermissionList) {
                String url = sysPermission.getSysResource().getUrl();
                List<String> roleList = SYS_RESOURCE_ROLE_MAP.getOrDefault(url,null);
                if (ListUtils.isEmpty(roleList)) {
                    roleList = new ArrayList<>();
                    SYS_RESOURCE_ROLE_MAP.put(url, roleList);
                }
                roleList.add(sysPermission.getSysRole().getCode());
            }
        }
    }

    public static void setCacheUrlMatcherList(List<AntPathRequestMatcher> requestMatcherList, boolean reset) {
        if (reset) {
            SYS_URL_MATCHER_LIST = new ArrayList<>();
        }
        if (Validator.isNotNull(requestMatcherList)) {
            SYS_URL_MATCHER_LIST.addAll(requestMatcherList);
        }
    }
}
