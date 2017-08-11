package com.wayne.sparrow.app.controller.main;

import com.wayne.sparrow.core.common.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String main() {
        log.info("come on");
        return getModel() + "/main";
    }
}
