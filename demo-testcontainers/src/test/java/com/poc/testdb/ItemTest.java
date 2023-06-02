package com.poc.testdb;

import com.poc.testdb.model.Item;
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
class ItemTest extends AbstractIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImageRepository itemImageRepository;

    @Test
    @Transactional
    void testCreateItem() {
        Item item = new Item();
        item.setItemName("Test Item");
        item.setItemPrice(100.00);
        item.setItemDetail("Test Detail");
        item.setItemRating(4.5f);

        Item savedItem = itemRepository.save(item);

        assertNotNull(savedItem);
        assertNotNull(savedItem.getItemId());
        assertEquals("Test Item", savedItem.getItemName());
        assertEquals(100.00, savedItem.getItemPrice());
        assertEquals("Test Detail", savedItem.getItemDetail());
        assertEquals(4.5f, savedItem.getItemRating());
    }

    @Test
    @Transactional
    void testFindItem() {
        Item item = new Item();
        item.setItemName("Test Item");
        item.setItemPrice(100.00);
        item.setItemDetail("Test Detail");
        item.setItemRating(4.5f);
        Item savedItem = itemRepository.save(item);

        Item foundItem = itemRepository.findById(savedItem.getItemId()).orElse(null);

        assertNotNull(foundItem);
        assertEquals(savedItem.getItemId(), foundItem.getItemId());
    }


}