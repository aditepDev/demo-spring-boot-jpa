package com.demo.shop.item.business;

import com.demo.shop.item.model.Item;
import com.demo.shop.item.repository.ItemRepository;
import com.demo.shop.item.response.ItemDetailResponse;
import com.demo.shop.item.response.ItemFullDetailResponse;
import com.demo.shop.item.service.ItemService;
import com.demo.shop.mork.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemBusinessTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;
    @InjectMocks
    ItemBusiness itemBusiness;


    @BeforeEach
    void setup() {
        itemService.setItemRepository(itemRepository);
        itemBusiness.setItemService(itemService);
    }

    @Test
    @DisplayName("แสดงสินค้่าทั้งหมดในร้าน")
    void findItemAll() {
        // Arrange
        List<Item> items = MockData.getItems();
        when(itemService.findAll()).thenReturn(items);
        // Act
        List<ItemDetailResponse> result = itemBusiness.findItemAll();
        // assert , verify
        assertEquals(1,result.size());

    }

    @Test
    @DisplayName("ค้นหาสินค้าด้วยชื่อสินค้า")
    void findAllByItemNameLike() {
        // Arrange
        List<Item> items = MockData.getItems();
        when(itemService.findAllByItemNameLike("name")).thenReturn(items);
        // Act
        List<ItemDetailResponse> result = itemBusiness.findAllByItemNameLike("name");
        // assert , verify
        assertEquals(1,result.size());
    }

    @Test
    @DisplayName("แสดงสินค้าตาม Id ที่เลือก")
    void findItemOneById() {
        // Arrange
        Item item = MockData.getItem();
        when(itemService.findOneById(1L)).thenReturn(Optional.of(item));
        // Acc
        ItemFullDetailResponse result = itemBusiness.findItemOneById(1L);
        // assert , verify
        assertEquals(1,result.getItemId());

    }


}