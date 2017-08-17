package com.wayne.sparrow.app.controller.main;

import com.wayne.sparrow.app.vo.MenuNode;
import com.wayne.sparrow.core.common.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wayne on 2017/8/8.
 */
@Controller
@Slf4j
public class MainController extends BaseController {

    @Override
    public String getModel() {
        return "main";
    }

    @GetMapping("/main")
    public String main(Model model) {
        int top = 5;
        int sub = 5;
        int subsub = 5;
        List<MenuNode> menuNodeList = new ArrayList<>();
        while (top-- > 0) {
            MenuNode menuNode = new MenuNode("top"+top);
            menuNodeList.add(menuNode);
            while (sub-- >0) {
                MenuNode subMenuNode = new MenuNode("sub"+sub);
                menuNode.addSubMenu(subMenuNode);
                while (subsub-- > 0 ) {
                    MenuNode subsubMenuNode = new MenuNode("subsub"+sub);
                    subMenuNode.addSubMenu(subsubMenuNode);
                }
            }
        }
        System.out.println(menuNodeList);
        model.addAttribute("menuNodeList", menuNodeList);
        log.info("come on");
        return getModel() + "/main";
    }
}
