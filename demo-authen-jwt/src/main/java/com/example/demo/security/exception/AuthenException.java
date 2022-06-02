package com.example.demo.security.exception;

import com.example.demo.exception.BaseException;
import org.springframework.http.HttpStatus;

public class AuthenException extends BaseException {


    private static final long serialVersionUID = 1884987268860286201L;

    public AuthenException(String code, HttpStatus status) {
        super("authen."+code, status);
    }

    public static AuthenException authenUsernameEmpty() {
        return new AuthenException("username.empty", HttpStatus.BAD_REQUEST);
    }

    public static AuthenException authenPasswordEmpty() {
        return new AuthenException("password.empty", HttpStatus.BAD_REQUEST);
    }

    public static AuthenException authenIncorrect() {
        return new AuthenException("username.password.incorrect", HttpStatus.BAD_REQUEST);
    }
}
