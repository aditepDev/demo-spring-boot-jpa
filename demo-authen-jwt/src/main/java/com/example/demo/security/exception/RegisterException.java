package com.example.demo.security.exception;

import com.example.demo.exception.BaseException;

public class RegisterException extends BaseException {


    private static final long serialVersionUID = 1884987268860286201L;

    public RegisterException(String code) {
        super("register."+code);
    }

    public static RegisterException usernameIsEmpty() {
        return new RegisterException("username.isEmpty");
    }
    public static RegisterException passwordIsEmpty() {
        return new RegisterException("password.isEmpty");
    }
    public static RegisterException lowRate() {
        return new RegisterException("salary.low.rate");
    }
    public static RegisterException usernameDuplicate() {
        return new RegisterException("username.duplicate");
    }
    public static RegisterException phoneNumberIncorrect() {
        return new RegisterException("phoneNumber.incorrect");
    }

}
