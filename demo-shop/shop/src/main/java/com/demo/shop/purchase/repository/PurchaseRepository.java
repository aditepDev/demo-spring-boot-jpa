package com.demo.shop.purchase.repository;

import com.demo.shop.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase>  findOneByInvoiceNo(String invoiceNo);
}
