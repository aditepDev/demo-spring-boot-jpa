package com.poc.testdb;

import com.poc.testdb.model.Item;
import com.poc.testdb.model.ItemImage;
import com.poc.testdb.repository.ItemImageRepository;
import com.poc.testdb.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ItemImageTest  extends AbstractIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImageRepository itemImageRepository;

    @Test
    @Transactional
    void testCreateItemImage() {
        Item item = new Item();
        item.setItemName("Test Item");
        item.setItemPrice(100.00);
        item.setItemDetail("Test Detail");
        item.setItemRating(4.5f);
        Item savedItem = itemRepository.save(item);

        ItemImage itemImage = new ItemImage();
        itemImage.setItemImage("Test Image");
        itemImage.setItem(savedItem);

        ItemImage savedItemImage = itemImageRepository.save(itemImage);

        assertNotNull(savedItemImage);
        assertNotNull(savedItemImage.getItemImageId());
        assertEquals("Test Image", savedItemImage.getItemImage());
        assertEquals(savedItem.getItemId(), savedItemImage.getItem().getItemId());
    }
}
