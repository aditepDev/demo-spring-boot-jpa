package com.demo.shop.item.repository;

import com.demo.shop.item.model.Item;
import com.demo.shop.mork.MockData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("ค้นหาสินค้าด้วยชื่อสินค้า")
    void findAllByItemNameContainingIgnoreCase() {
        // Arrange
        Item item = MockData.getItem();
        itemRepository.save(item);
        // ACt
        List<Item> result = itemRepository.findAllByItemNameContainingIgnoreCase("mame");
        // Assert
        assertEquals(1,result.size());
    }
}