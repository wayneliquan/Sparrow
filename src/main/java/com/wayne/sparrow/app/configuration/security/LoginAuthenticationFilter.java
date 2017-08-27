package com.wayne.sparrow.app.configuration.security;

import com.wayne.sparrow.app.exception.CaptchaException;
import com.wayne.sparrow.core.constants.SessionConstants;
import com.wayne.sparrow.core.constants.SysConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanliquan on 17-8-25.
 * Description:
 */
@Slf4j
@Data
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha";
    private String captchaKey = SPRING_SECURITY_FORM_CAPTCHA_KEY;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (SysConstants.isShowCaptcha()) {
            String requestCaptcha = request.getParameter(captchaKey);
            String genCaptcha = SessionConstants.get(SysConstants.CAPTCHA_SESSION_KEY, "");
            log.info("开始校验验证码，生成的验证码为：" + genCaptcha + " ，输入的验证码为：" + requestCaptcha);
            if (!genCaptcha.equalsIgnoreCase(requestCaptcha)) {
                request.getSession().setAttribute(SysConstants.CAPTCHA_SESSION_KEY, "");
                throw new CaptchaException("captcha code not matched!");
            }
        }
        int count = SessionConstants.get(SysConstants.ATTEMPT_LOGIN_COUNT_SESSION_KEY, 0);
        SessionConstants.put(SysConstants.ATTEMPT_LOGIN_COUNT_SESSION_KEY, ++count);
        Authentication authentication = super.attemptAuthentication(request, response);
        // 如果没有抛出异常， 应该是登录成功了。
        SessionConstants.put(SysConstants.ATTEMPT_LOGIN_COUNT_SESSION_KEY, 0);
        return authentication;
    }
}
