package com.poc.testdb.model;

import com.poc.testdb.model.Item;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="item_image")
public class ItemImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_image_id")
    private long itemImageId;
    @Column(name="item_image")
    private String itemImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id" , nullable = false)
    private Item item;
}