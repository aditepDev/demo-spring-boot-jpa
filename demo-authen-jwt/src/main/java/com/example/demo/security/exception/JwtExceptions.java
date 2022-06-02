package com.example.demo.security.exception;

import com.example.demo.exception.BaseException;

public class JwtExceptions extends BaseException {
    private static final long serialVersionUID = 1L;

    public JwtExceptions(String code) {
        super("jwt." + code);
    }

    public static JwtExceptions jwtEmpty() {
        return new JwtExceptions("empty");
    }

    public static JwtExceptions jwtBearerMissing() {
        return new JwtExceptions("bearer.missing");
    }


    public static JwtExceptions jwtUnitMissing() {
        return new JwtExceptions("unit.missing");
    }
}
