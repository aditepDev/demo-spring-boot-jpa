package com.demo.shop.item.business;

import com.demo.shop.item.response.ItemDetailResponse;
import com.demo.shop.item.response.ItemFullDetailResponse;
import com.demo.shop.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemBusiness {

    @Autowired
    ItemService itemService;

    public List<ItemDetailResponse> findItemAll() {
        return ItemDetailResponse.packItemDetail(itemService.findAll());
    }

    public List<ItemDetailResponse> findAllByItemNameLike(String name) {
        return ItemDetailResponse.packItemDetail(itemService.findAllByItemNameLike(name));
    }

    public ItemFullDetailResponse findItemOneById(long itemId){
        return ItemFullDetailResponse.packItemFullDetail(itemService.findOneById(itemId));
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

}
