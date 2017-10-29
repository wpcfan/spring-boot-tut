package dev.local.auth.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UsernameTakenException extends AuthenticationException {

    private final static long serialVersionUID = 1L;

    public UsernameTakenException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameTakenException(String msg) {
        super(msg);
    }
}
