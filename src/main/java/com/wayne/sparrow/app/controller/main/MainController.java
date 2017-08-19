package com.wayne.sparrow.app.controller.main;

import com.alibaba.fastjson.JSON;
import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.service.system.SysUserService;
import com.wayne.sparrow.app.vo.EntityToVO;
import com.wayne.sparrow.app.vo.MenuNode;
import com.wayne.sparrow.core.common.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by Wayne on 2017/8/8.
 */
@Controller
@Slf4j
public class MainController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public String getModulePath() {
        return "main";
    }

    @GetMapping("/main")
    public String main(Model model) {
        List<SysResource> sysResourceList = sysUserService.listCurrentUserResource();
        List<MenuNode> menuNodeList = EntityToVO.resourceToMenuNode(sysResourceList);
        String menuTreeData = JSON.toJSONString(menuNodeList);
        model.addAttribute("menuTreeData", menuTreeData);
        return "main/main";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/sysUser/list";
    }
}
