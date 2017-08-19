package com.wayne.sparrow.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanliquan on 17-7-3.
 * Description:
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test")
    public String test(Model model) {
        return "test/test";
    }

    @GetMapping("/verify")
    public String verify() {
        return "test/verify";
    }

    @GetMapping("/bootstrap-treeview")
    public String treeview() {
        return "test/bootstrap-treeview";
    }

    @GetMapping("/verify-jquery")
    public String verifyJquery() {
        return "test/verify-jquery";
    }

    @GetMapping("/home")
    public String home(){
        return "test/home";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "spring security admin";
    }

    @GetMapping(path = {"","/", "/testUrl"})
    @ResponseBody
    public String testUrl(HttpServletRequest request, HttpServletResponse response) {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length() + 1);
        //url去掉?之后的参数
        if(url.indexOf("?") != -1){
            url = url.substring(0,url.indexOf("?"));
        }
        String requestPath = getRequestPath(request);// 用户访问的资源地址
        System.out.println(url.substring(0,url.indexOf("/")+1));
        return "url:"+url + "   requestPath:" + requestPath;
    }

    public static String getRequestPath(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        //添加查询参数
        if(request.getQueryString() != null && !"".equals(request.getQueryString())){
            requestPath = requestPath + "?" + request.getQueryString();
        }
        // 去掉其他参数
        if (requestPath.indexOf("&") > -1) {
            requestPath = requestPath.substring(0, requestPath.indexOf("&"));
        }
        requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
        return requestPath;
    }
}
