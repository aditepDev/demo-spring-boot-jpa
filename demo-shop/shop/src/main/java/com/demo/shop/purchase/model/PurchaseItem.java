package com.demo.shop.purchase.model;

import com.demo.shop.item.model.Item;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name="purchase_item")
public class PurchaseItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseItemId;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "purchase_purchase_id")
    private Purchase purchase;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "item_item_id")
    private Item item;
    private String itemName;
    private String itemDetail;
    private String itemImage;
    private int qty;
    private double price;

}
