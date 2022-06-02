package com.demo.shop.purchase.exception;

import com.demo.shop.exception.BaseException;

public class CheckoutException extends BaseException {
    protected CheckoutException(String code) {
        super("checkout." + code);
    }

    public  static CheckoutException custom(String text){
        return new CheckoutException(text);
    }
    public  static CheckoutException itemNull(){
        return new CheckoutException("item.null");
    }
}
