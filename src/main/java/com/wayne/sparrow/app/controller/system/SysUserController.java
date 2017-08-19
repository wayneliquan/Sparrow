package com.wayne.sparrow.app.controller.system;

import com.alibaba.fastjson.JSON;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.entity.system.SysUser;
import com.wayne.sparrow.app.service.system.SysAuthorizationService;
import com.wayne.sparrow.app.service.system.SysRoleService;
import com.wayne.sparrow.app.service.system.SysUserService;
import com.wayne.sparrow.app.vo.EntityToVO;
import com.wayne.sparrow.app.vo.TreeNode;
import com.wayne.sparrow.core.common.OperationMessage;
import com.wayne.sparrow.core.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-10.
 * Description:
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysAuthorizationService sysAuthorizationService;

    @Override
    public String getModulePath() {
        return "system/sysUser";
    }

    @PostMapping("/grant")
    @ResponseBody
    public OperationMessage grant(Long sysUserId, Long[] sysRoleIds) {
        OperationMessage opMsg = initOpMsg();
        System.out.println(Arrays.toString(sysRoleIds));
        if (sysRoleIds != null) {
            SysUser sysUser = sysUserService.findById(sysUserId);
            List<SysRole> sysRoleList = sysRoleService.findByIds(sysRoleIds);
            sysAuthorizationService.authorize(sysUser, sysRoleList);
        }
        return opMsg;
    }

    @GetMapping("/grantRole")
    public String grantRole(Model model, Long sysUserId) {
        model.addAttribute("sysUserId", sysUserId);
        List<SysRole> sysRoleList = sysRoleService.listAll();
        List<TreeNode> nodes = EntityToVO.sysRoleToTree(sysRoleList);
        String roleData = JSON.toJSONString(nodes);
        model.addAttribute("roleData", roleData);
        return getModulePath() + "-grantRole";
    }

    @GetMapping("/input")
    public String input(Model model, Long sysUserId) {
        SysUser sysUser = sysUserService.findById(sysUserId);
        model.addAttribute("sysUser", sysUser);
        return getModulePath() + "-input";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<SysUser> sysUserList = sysUserService.listAll();
        model.addAttribute("sysUserList", sysUserList);
        return getModulePath() + "-list";
    }

    @PostMapping("/save")
    @ResponseBody
    public OperationMessage save(Model model, SysUser sysUser) {
        OperationMessage opMsg = initOpMsg();
        System.out.println(sysUser);
        sysUserService.save(sysUser);
        return opMsg;
    }
}
