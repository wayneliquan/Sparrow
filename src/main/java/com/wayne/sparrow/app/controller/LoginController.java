package com.wayne.sparrow.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhanliquan on 17-8-5.
 * Description:
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login/login";
    }
}
