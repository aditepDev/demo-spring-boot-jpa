package com.demo.shop.purchase.exception;

import com.demo.shop.exception.BaseException;

public class BillException extends BaseException {
    protected BillException(String code) {
        super("checkout." + code);
    }

    public  static BillException custom(String text){
        return new BillException(text);
    }
    public  static BillException invoiceNonull(){
        return new BillException("invoiceNo.null");
    }
}
