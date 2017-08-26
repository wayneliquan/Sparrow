package com.wayne.sparrow.app.configuration.security;

import com.wayne.sparrow.app.exception.CaptchaException;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanliquan on 17-8-25.
 * Description:
 */
@Data
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public static final String PASS_ERROR_URL = "/login?error";
    public static final String CODE_ERROR_URL = "/login?code";
    public static final String EXPIRED_URL = "/login?expire";
    public static final String DISABLED_URL = "/login?disabled";
    public static final String LOCKED_URL = "/login?locked";
    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof CaptchaException) {
            getRedirectStrategy().sendRedirect(request, response, CODE_ERROR_URL);
        } else {
            getRedirectStrategy().sendRedirect(request, response, PASS_ERROR_URL);
        }
    }
}
