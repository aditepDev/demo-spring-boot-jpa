package com.demo.shop.purchase.business;

import com.demo.shop.exception.BaseException;
import com.demo.shop.item.model.Item;
import com.demo.shop.item.model.ItemImage;
import com.demo.shop.item.service.ItemService;
import com.demo.shop.member.exception.MemberException;
import com.demo.shop.member.model.Member;
import com.demo.shop.member.model.MemberWallet;
import com.demo.shop.member.service.MemberService;
import com.demo.shop.purchase.exception.BillException;
import com.demo.shop.purchase.exception.CheckoutException;
import com.demo.shop.purchase.model.Purchase;
import com.demo.shop.purchase.model.PurchaseItem;
import com.demo.shop.purchase.requests.ItemList;
import com.demo.shop.purchase.requests.CheckOutRequest;
import com.demo.shop.purchase.response.BillDetail;
import com.demo.shop.purchase.response.CheckOutResponse;
import com.demo.shop.purchase.response.Checkout;
import com.demo.shop.purchase.response.PurchaseResponse;
import com.demo.shop.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseBusiness {

    @Autowired
    PurchaseService purchaseService;
    @Autowired
    ItemService itemService;
    @Autowired
    MemberService memberService;
    public PurchaseResponse checkOut(CheckOutRequest checkOutRequest, Member member,String invoiceNo) throws BaseException {

        Purchase purchase = new Purchase();
        purchase.setName(checkOutRequest.getUserDetail().getUserName());
        purchase.setAddress(checkOutRequest.getUserDetail().getUserAddress());
        purchase.setMember(member);
        purchase.setTel(checkOutRequest.getUserDetail().getUserTel());
        purchase.setInvoiceNo(invoiceNo);
        List<PurchaseItem> purchaseItems = new ArrayList<>();

        int qty = 0;
        double total = 0;
        for (ItemList itemList : checkOutRequest.getItemList()) {
            Optional<Item> item = itemService.findOneById(itemList.getItemId());
            if (item.isEmpty()) {
                throw CheckoutException.itemNull();
            }
            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setItemName(item.get().getItemName());
            purchaseItem.setItem(item.get());
            purchaseItem.setItemDetail(item.get().getItemDetail());

            String itemItem = null;
            if (!item.get().getItemImage().isEmpty()) {
                itemItem = item.get().getItemImage().stream().map(ItemImage::getItemImage).findFirst().get();
            }
            purchaseItem.setItemImage(itemItem);
            purchaseItem.setPrice(item.get().getItemPrice());
            purchaseItem.setPurchase(purchase);
            purchaseItem.setQty(itemList.getQty());
            purchaseItems.add(purchaseItem);
            qty += itemList.getQty();
            total += itemList.getQty() * item.get().getItemPrice();
        }
        purchase.setTotal(total);
        purchase.setQty(qty);
        purchase.setPurchaseItem(purchaseItems);


        double memberWalletOld = member.getMemberWallet().getWallet();
        pay(member, total);
        purchaseService.savePurchase(purchase);
        PurchaseResponse purchaseResponse  = new PurchaseResponse();
        purchaseResponse.setMemberWalletOld(memberWalletOld);
        purchaseResponse.setMemberWallet( member.getMemberWallet().getWallet());
        purchaseResponse.setBillInvoiceNo(invoiceNo);
        purchaseResponse.setTotalPrice(total);


        return purchaseResponse;


    }
    public Member pay(Member member, double total) throws MemberException {
        MemberWallet memberWallet =  member.getMemberWallet();
        double priceTotal = memberWallet.getWallet() - total;
        if((priceTotal) >= 0){
            memberWallet.setWallet(priceTotal);
            member.setMemberWallet(memberWallet);
            return memberService.saveMembe(member);
        }else{
            throw  MemberException.walletNotEnough();
        }

    }
    public CheckOutResponse bill(String invoiceNo) throws BillException {
        Optional<Purchase>  purchase = purchaseService.findOneByInvoiceNo(invoiceNo);
        if (purchase.isEmpty()) {
            throw BillException.invoiceNonull();
        }
        CheckOutResponse checkOutResponse = new CheckOutResponse();
        Checkout checkout = new Checkout();
        checkout.setTotalPrice(purchase.get().getTotal());
        checkout.setTotalQty(purchase.get().getQty());
        BillDetail billDetail = new BillDetail();
        billDetail.setBillDate(purchase.get().getCreatedAt());
        billDetail.setBillName(purchase.get().getName());
        billDetail.setBillInvoiceNo(purchase.get().getInvoiceNo());
        checkOutResponse.setCheckout(checkout);
        checkOutResponse.setBillDetail(billDetail);
       return checkOutResponse;
    }

    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
