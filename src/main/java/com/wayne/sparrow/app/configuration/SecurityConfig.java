package com.wayne.sparrow.app.configuration;

import com.wayne.sparrow.app.configuration.security.LoginUserDetailsService;
import com.wayne.sparrow.app.configuration.security.MyFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Wayne on 2017/8/8.
 */
@EnableWebSecurity
@Configurable
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    /**
     * 配置登录有关的权限, 标示某个用户有某些角色
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new LoginUserDetailsService());
//        auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("123").roles("USER", "SYS_ADMIN");
    }

    /**
     * 这里是授权的配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 添加自定义的权限过滤器到spring-security的过滤器链中
        http.authorizeRequests().antMatchers("/main").authenticated();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/error").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // for test
        http.authorizeRequests().antMatchers("/test/admin").hasRole("SYS_ADMIN");
        http.authorizeRequests().antMatchers("/test/home").access("hasRole('ROLE_USER')"); //基于表达式的写法

        // 在配置myFilterSecurityInterceptor之后就不起作用喽，原因: FilterSecurityInterceptor 默认只会执行一次
        //     myFilterSecurityInterceptor.setObserveOncePerRequest(false);
        http.authorizeRequests().antMatchers("/**/*.html").permitAll();
        http.authorizeRequests().antMatchers("/**/*.js").access("permitAll"); //基于表达式的写法
        http.authorizeRequests().antMatchers("/**/*.css").permitAll();
        http.authorizeRequests().antMatchers("/**/*.css.map").permitAll();

        // 允许在iframe里面显示
        http.headers().frameOptions().sameOrigin();

//        http.authorizeRequests().antMatchers("/**").access("authenticated");
//        http.formLogin();
        http.formLogin().loginPage("/login");
        http.csrf().disable();
        http.rememberMe().key("Sparrow");
        http.logout().logoutSuccessUrl("/login").logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        myFilterSecurityInterceptor.setObserveOncePerRequest(false);
        myFilterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean());
        http.addFilterAfter(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

}
