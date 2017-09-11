package com.wayne.sparrow.app.configuration;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"), //忽略资源
                @WebInitParam(name = "sessionStatMaxCount", value = "1000"),
                @WebInitParam(name = "profileEnable", value = "true")
        }
)
public class DruidStatFilter extends WebStatFilter {

}