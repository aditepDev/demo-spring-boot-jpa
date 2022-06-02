package com.demo.shop.purchase.controller;

import com.demo.shop.item.model.Item;
import com.demo.shop.item.repository.ItemRepository;
import com.demo.shop.member.model.Member;
import com.demo.shop.member.repository.MemberRepository;
import com.demo.shop.mork.MockData;
import com.demo.shop.mork.MockPayload;
import com.demo.shop.purchase.model.Purchase;
import com.demo.shop.purchase.repository.PurchaseRepository;
import com.demo.shop.purchase.requests.CheckOutRequest;
import com.demo.shop.purchase.response.CheckOutResponse;
import com.demo.shop.purchase.response.PurchaseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PurchaseControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @MockBean
    PurchaseRepository purchaseRepository;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    MemberRepository memberRepository;
    @Test
    @DisplayName("สร้างคำสั่งซื้อ")
    void purchase() {

        // Arrange
        String invoiceNo =  UUID.randomUUID().toString();
        Member member = MockData.getMember();
        Item item = MockData.getItem();
        CheckOutRequest checkOutRequest = MockPayload.getCheckOutRequest();
        Purchase purchase = MockData.getPurchase(invoiceNo, member, item);
        purchase.setPurchaseId(0);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        // Act

        ResponseEntity<PurchaseResponse> result = testRestTemplate.postForEntity("/check_out",checkOutRequest, PurchaseResponse.class);

        // assert , verify
        assertEquals(201,result.getStatusCodeValue());
    }

    @Test
    @DisplayName("ดู bill ที่สั่งซื้อ")
    void bill() {
        String invoiceNo = "invoicenoId";
        Member member = MockData.getMember();
        Item item = MockData.getItem();
        Purchase purchase = MockData.getPurchase(invoiceNo, member, item);
        // Arrange
        when(purchaseRepository.findOneByInvoiceNo(invoiceNo)).thenReturn(Optional.of(purchase));
        // Act
        CheckOutResponse result = testRestTemplate.getForObject("/bill/invoicenoId", CheckOutResponse.class);
        // Assert
        assertEquals(invoiceNo,result.getBillDetail().getBillInvoiceNo());
        assertEquals(20,result.getCheckout().getTotalPrice());
    }
}