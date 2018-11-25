package com.wayne.sparrow.app.controller.system;

import com.alibaba.fastjson.JSON;
import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.service.system.SysAuthorizationService;
import com.wayne.sparrow.app.service.system.SysResourceService;
import com.wayne.sparrow.app.service.system.SysRoleService;
import com.wayne.sparrow.app.vo.EntityToVO;
import com.wayne.sparrow.app.vo.TreeNode;
import com.wayne.sparrow.core.common.OperationMessage;
import com.wayne.sparrow.core.common.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhanliquan on 17-8-10.
 * Description:
 */
@Slf4j
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private SysAuthorizationService sysAuthorizationService;

    @Override
    public String getModulePath() {
        return "system/sysRole";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<SysRole> sysRoleList = sysRoleService.listAll();
        model.addAttribute("sysRoleList", sysRoleList);
        return getModulePath() + "-list";
    }

    @GetMapping("/input")
    public String input(Model model, Long roleId) {
        if (roleId != null) {
            SysRole sysRole = sysRoleService.findById(roleId);
            model.addAttribute("sysRole", sysRole);
        }
        return getModulePath() + "-input";
    }

    @PostMapping("/save")
    @ResponseBody
    public OperationMessage save(SysRole sysRole) {
        OperationMessage opMsg = initOpMsg();
        sysRoleService.save(sysRole);
        return opMsg;
    }

    @GetMapping("/authorization")
    public String authorization(Model model, Long sysRoleId) {
        List<Long> authorizedResourceIdList = sysAuthorizationService.findResourceIdsByRoleId(sysRoleId);
        List<SysResource> sysResourceList = sysResourceService.listAll();
        List<TreeNode> nodes = EntityToVO.resourceToTree(sysResourceList, authorizedResourceIdList);
        String resourceData = JSON.toJSONString(nodes);
        model.addAttribute("sysRoleId", sysRoleId);
        model.addAttribute("resourceData", resourceData);
        return getModulePath() + "-authorization";
    }

    /**
     * 保存授权
     * @param sysRoleId
     * @param resourceIds
     * @return
     */
    @PostMapping("/authorize")
    @ResponseBody
    public OperationMessage authorize(Long sysRoleId, Long[] resourceIds) {
        OperationMessage opMsg = initOpMsg();
        SysRole sysRole = sysRoleService.findById(sysRoleId);
        List<SysResource> resourceList = sysResourceService.findByIds(resourceIds);
        log.info("resourceList" + resourceList);
        sysAuthorizationService.authorize(sysRole, resourceList);
        return opMsg;
    }
}
