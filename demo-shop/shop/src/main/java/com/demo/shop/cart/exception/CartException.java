package com.demo.shop.cart.exception;

import com.demo.shop.exception.BaseException;

public class CartException extends BaseException {
    protected CartException(String code) {
        super("cart."+code);
    }
    public  static CartException custom(String text){
        return new CartException(text);
    }
    public  static CartException itemNull(){
        return new CartException("item.null");
    }
}
