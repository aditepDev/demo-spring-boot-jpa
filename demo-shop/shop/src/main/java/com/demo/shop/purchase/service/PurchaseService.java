package com.demo.shop.purchase.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.shop.purchase.model.Purchase;
import com.demo.shop.purchase.repository.PurchaseItemRepository;
import com.demo.shop.purchase.repository.PurchaseRepository;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseItemRepository purchaseItemRepository;
    public Optional<Purchase>  findOneByInvoiceNo(String invoiceNo) {
        return  purchaseRepository.findOneByInvoiceNo(invoiceNo);
    }
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public void setPurchaseItemRepository(PurchaseItemRepository purchaseItemRepository) {
        this.purchaseItemRepository = purchaseItemRepository;
    }
}
