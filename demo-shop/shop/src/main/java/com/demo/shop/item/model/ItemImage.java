package com.demo.shop.item.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="item_image")
public class ItemImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemImageId;
    private String itemImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id" , nullable = false)
    private Item item;
}
