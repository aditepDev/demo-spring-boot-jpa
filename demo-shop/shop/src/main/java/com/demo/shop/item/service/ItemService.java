package com.demo.shop.item.service;

import com.demo.shop.item.model.Item;
import com.demo.shop.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> findAll(){
    return itemRepository.findAll();
    }

    public List<Item> findAllByItemNameLike(String name){
        return itemRepository.findAllByItemNameContainingIgnoreCase(name);
    }

    public Optional<Item> findOneById(long itemId){
        return itemRepository.findById(itemId);
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
