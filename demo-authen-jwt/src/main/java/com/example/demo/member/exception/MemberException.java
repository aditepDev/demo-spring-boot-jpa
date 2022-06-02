package com.example.demo.member.exception;

import com.example.demo.exception.BaseException;

public class MemberException extends BaseException {

    public MemberException(String code) {
        super("member." + code);
    }

    public static MemberException uuidEmpty() {
        return new MemberException("uuid.empty");
    }

}
