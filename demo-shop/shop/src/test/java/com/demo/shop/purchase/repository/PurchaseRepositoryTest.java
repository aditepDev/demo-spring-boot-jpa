package com.demo.shop.purchase.repository;

import com.demo.shop.item.model.Item;
import com.demo.shop.member.model.Member;
import com.demo.shop.mork.MockData;
import com.demo.shop.purchase.model.Purchase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PurchaseRepositoryTest {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Test
    @DisplayName("ค้นหาคำสั่งซื้อด้วย InvoiceNo")
    void findOneByInvoiceNo() {
        // Arrange
        String invoiceNo = "invoicenoId";
        Member member = MockData.getMember();
        Item item = MockData.getItem();
        Purchase purchase = MockData.getPurchase(invoiceNo, member, item);
        purchaseRepository.save(purchase);
        // Act
        Optional<Purchase> result = purchaseRepository.findOneByInvoiceNo(invoiceNo);
        // Assert
        assertEquals(invoiceNo,result.get().getInvoiceNo());
    }
}