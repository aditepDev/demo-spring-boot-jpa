package com.demo.shop.exception;

@SuppressWarnings("serial")
public abstract class BaseException extends Exception {

    protected BaseException(String code) {
        super(code);
    }

}