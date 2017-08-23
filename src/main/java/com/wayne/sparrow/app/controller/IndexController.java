package com.wayne.sparrow.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zhanliquan on 17-8-21.
 * Description:
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/main";
    }
}
