package com.wayne.sparrow.app.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * Created by zhanliquan on 17-8-25.
 * Description:
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }

    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }
}
