package com.wayne.Sparrow.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Wayne on 2017/8/8.
 */
@Controller
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
