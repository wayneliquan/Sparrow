package com.wayne.sparrow.app.controller;

import com.wayne.sparrow.core.constants.SessionConstants;
import com.wayne.sparrow.core.constants.SysConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhanliquan on 17-8-5.
 * Description:
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(Model model) {
        int count = SessionConstants.get(SysConstants.ATTEMPT_LOGIN_COUNT_SESSION_KEY, 0);
        if (count > SysConstants.MAX_ATTEMPT_LOGIN_COUNT) {
            model.addAttribute("needCaptcha", true);
        }
        return "login/login";
    }
}
