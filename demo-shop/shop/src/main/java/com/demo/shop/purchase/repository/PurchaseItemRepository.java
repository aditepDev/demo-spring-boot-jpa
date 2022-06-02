package com.demo.shop.purchase.repository;

import com.demo.shop.purchase.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem,Long> {
}
