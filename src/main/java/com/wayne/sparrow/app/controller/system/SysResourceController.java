package com.wayne.sparrow.app.controller.system;

import com.alibaba.fastjson.JSON;
import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.service.system.SysResourceService;
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
 * Created by zhanliquan on 17-8-14.
 * Description:
 */
@Slf4j
@Controller
@RequestMapping("/sysResource")
public class SysResourceController extends BaseController {

    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public String getModulePath() {
        return "system/sysResource";
    }

    @Override
    public String getModuleName() {
        return "系统资源列表";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<SysResource> sysResourceList = sysResourceService.listAll();
        model.addAttribute("sysResourceList", sysResourceList);

        return getModulePath() + "-list";
    }

    @GetMapping("/input")
    public String input(Model model, Long resourceId) {
        if (resourceId != null && resourceId > 0) {
            SysResource sysResource = sysResourceService.findById(resourceId);
            model.addAttribute("sysResource", sysResource);
        }
        return getModulePath() + "-input";
    }

    @PostMapping("/save")
    @ResponseBody
    public OperationMessage save(SysResource sysResource) {
        System.out.println(sysResource);
        OperationMessage opMsg = initOpMsg();
        sysResourceService.save(sysResource);
        return opMsg;
    }

    @GetMapping("/tree")
    public String tree(Model model) {
        List<SysResource> sysResourceList = sysResourceService.listAll();
        List<TreeNode> nodes = EntityToVO.resourceToTree(sysResourceList);
        String resourceData = JSON.toJSONString(nodes);
        System.out.println(resourceData);
        model.addAttribute("resourceData", resourceData);
        return getModulePath() + "-tree";
    }
}
