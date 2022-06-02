package com.demo.shop.purchase.response;

import lombok.Data;

@Data
public class PurchaseResponse {
    private String billInvoiceNo;
    private double totalPrice;
    private double memberWalletOld;
    private double memberWallet;
}
