package com.demo.shop.member.exception;

import com.demo.shop.exception.BaseException;

public class MemberException extends BaseException {
    protected MemberException(String code) {
        super("member." + code);
    }

    public  static MemberException custom(String text){
        return new MemberException(text);
    }
    public  static MemberException walletNotEnough(){
        return new MemberException("wallet.notEnough");
    }
}
