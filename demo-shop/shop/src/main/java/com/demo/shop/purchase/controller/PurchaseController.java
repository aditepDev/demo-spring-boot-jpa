package com.demo.shop.purchase.controller;

import com.demo.shop.exception.BaseException;
import com.demo.shop.member.model.Member;
import com.demo.shop.member.service.MemberService;
import com.demo.shop.purchase.business.PurchaseBusiness;
import com.demo.shop.purchase.exception.BillException;
import com.demo.shop.purchase.response.CheckOutResponse;
import com.demo.shop.purchase.requests.CheckOutRequest;
import com.demo.shop.purchase.response.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class PurchaseController {

    @Autowired
    PurchaseBusiness purchaseBusiness;
    @Autowired
    MemberService memberService;

    @PostMapping(value = "/check_out")
    public ResponseEntity<PurchaseResponse> purchase(@RequestBody CheckOutRequest checkOutRequest) throws BaseException {
        Optional<Member> member = memberService.memberMork();
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        if(member.isPresent()){
            purchaseResponse = purchaseBusiness.checkOut(checkOutRequest, member.get(), UUID.randomUUID().toString());
        }
        return new ResponseEntity<>(purchaseResponse,HttpStatus.CREATED);
    }

    @GetMapping(value = "/bill/{invoiceNo}")
    public ResponseEntity<CheckOutResponse> bill(@PathVariable String invoiceNo) throws BillException {
        CheckOutResponse checkOutResponse = purchaseBusiness.bill(invoiceNo);
        return new ResponseEntity<>(checkOutResponse,HttpStatus.CREATED);
    }
}
