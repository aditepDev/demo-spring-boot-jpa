package com.demo.shop.item.repository;

import com.demo.shop.item.model.Item;
import com.demo.shop.item.model.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage,Long> {
    public ItemImage findTopByItem(Item item);

    public List<ItemImage> findAllByItem(Item item);
}
