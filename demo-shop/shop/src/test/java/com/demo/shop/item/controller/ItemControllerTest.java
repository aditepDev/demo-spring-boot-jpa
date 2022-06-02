package com.demo.shop.item.controller;

import com.demo.shop.item.model.Item;
import com.demo.shop.item.repository.ItemRepository;
import com.demo.shop.item.response.ItemDetailResponse;
import com.demo.shop.item.response.ItemFullDetailResponse;
import com.demo.shop.mork.MockData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private ItemRepository itemRepository;
    @Test
    @DisplayName("ดูสินค้าทั้งหมดในร้าน")
    void findProductAll() {

        // Arrange
        List<Item> items = MockData.getItems();
        when(itemRepository.findAll()).thenReturn(items);
        // Act
        ItemDetailResponse[] result = testRestTemplate.getForObject("/item/",ItemDetailResponse[].class);
        // assert
        assertEquals(1,result.length);
    }

    @Test
    @DisplayName("ค้นหาสินค้าด้วยชื่อสินค้า")
    void findProductFilter() {
        // Arrange
        String itemName = "mame";
        List<Item> items = MockData.getItems();
        when(itemRepository.findAllByItemNameContainingIgnoreCase(itemName)).thenReturn(items);
        // act
        ItemDetailResponse[] result = testRestTemplate.getForObject("/item?name=mame",ItemDetailResponse[].class);
        // assert
        assertEquals(1,result.length);
    }

    @Test
    @DisplayName("ค้นหาสินค้าด้วย ID สินค้า")
    void findItemOneByItemId() {
        // Arrange
        Item item = MockData.getItem();
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        // act
        ItemFullDetailResponse result = testRestTemplate.getForObject("/item/1", ItemFullDetailResponse.class);
        // assert
        assertEquals(1,result.getItemId());
    }
}