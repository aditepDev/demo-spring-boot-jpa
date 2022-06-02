package com.demo.shop.purchase.business;

import com.demo.shop.cart.response.CartResponse;
import com.demo.shop.exception.BaseException;
import com.demo.shop.item.model.Item;
import com.demo.shop.item.repository.ItemRepository;
import com.demo.shop.item.service.ItemService;
import com.demo.shop.member.model.Member;
import com.demo.shop.member.model.MemberWallet;
import com.demo.shop.member.repository.MemberRepository;
import com.demo.shop.member.service.MemberService;
import com.demo.shop.mork.MockData;
import com.demo.shop.mork.MockPayload;
import com.demo.shop.purchase.model.Purchase;
import com.demo.shop.purchase.repository.PurchaseItemRepository;
import com.demo.shop.purchase.repository.PurchaseRepository;
import com.demo.shop.purchase.requests.CheckOutRequest;
import com.demo.shop.purchase.response.CheckOutResponse;
import com.demo.shop.purchase.response.PurchaseResponse;
import com.demo.shop.purchase.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseBusinessTest {
    @Mock
    PurchaseItemRepository purchaseItemRepository;
    @Mock
    PurchaseRepository purchaseRepository;
    @Mock
    ItemRepository itemRepository;
    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    PurchaseBusiness purchaseBusiness;
    @InjectMocks
    PurchaseService purchaseService;
    @InjectMocks
    ItemService itemService;
    @InjectMocks
    MemberService memberService;
    @BeforeEach
    void setup() {
        purchaseService.setPurchaseItemRepository(purchaseItemRepository);
        purchaseService.setPurchaseRepository(purchaseRepository);
        itemService.setItemRepository(itemRepository);
        memberService.setMemberRepository(memberRepository);
        purchaseBusiness.setPurchaseService(purchaseService);
        purchaseBusiness.setItemService(itemService);
        purchaseBusiness.setMemberService(memberService);
    }


    @Test
    @DisplayName("สร้างคำสั่งซื้อ")
    void checkOut() throws BaseException {
        // Arrange
        String invoiceNo =  UUID.randomUUID().toString();
        Member member = MockData.getMember();
        Item item = MockData.getItem();
        CheckOutRequest checkOutRequest = MockPayload.getCheckOutRequest();
        Purchase purchase = MockData.getPurchase(invoiceNo, member, item);
        purchase.setPurchaseId(0);
        when(itemService.findOneById(1)).thenReturn(Optional.of(item));
        // Act
        PurchaseResponse result = purchaseBusiness.checkOut(checkOutRequest, member, invoiceNo);
        // assert , verify
        assertEquals(invoiceNo,result.getBillInvoiceNo());
        assertEquals(20,result.getTotalPrice());
        assertEquals(5000,result.getMemberWalletOld());
        assertEquals(4980,result.getMemberWallet());

    }

    @Test
    @DisplayName("จ่ายเงิน")
    void pay() throws BaseException {
        // Arrange
        Member member = MockData.getMember();
        double total = 1000;
        MemberWallet walletOld = member.getMemberWallet();
        double wallet = walletOld.getWallet() - total;
        member.getMemberWallet().setWallet(wallet);
        when(memberService.saveMembe(member)).thenReturn(member);
        // Act
        Member result = purchaseBusiness.pay(member, total);
        // assert , verify
        assertEquals(1,result.getMemberId());
        assertEquals(3000,result.getMemberWallet().getWallet());
    }

    @Test
    @DisplayName("ดู bill ที่สั่งซื้อ")
    void bill() throws BaseException {
        String invoiceNo = "invoicenoId";
        Member member = MockData.getMember();
        Item item = MockData.getItem();
        Purchase purchase = MockData.getPurchase(invoiceNo, member, item);
        // Arrange
        when(purchaseService.findOneByInvoiceNo(invoiceNo)).thenReturn(Optional.of(purchase));
        // Act
        CheckOutResponse result = purchaseBusiness.bill(invoiceNo);
        // assert , verify
        assertEquals(invoiceNo,result.getBillDetail().getBillInvoiceNo());
        assertEquals(20,result.getCheckout().getTotalPrice());

    }


}