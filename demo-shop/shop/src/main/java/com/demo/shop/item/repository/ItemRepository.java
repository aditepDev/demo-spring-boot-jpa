package com.demo.shop.item.repository;

import com.demo.shop.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    public List<Item> findAllByItemNameContainingIgnoreCase(String itemName);
}
