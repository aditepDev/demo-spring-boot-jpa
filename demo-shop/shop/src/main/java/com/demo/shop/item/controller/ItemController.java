package com.demo.shop.item.controller;

import com.demo.shop.item.business.ItemBusiness;
import com.demo.shop.item.response.ItemDetailResponse;
import com.demo.shop.item.response.ItemFullDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemBusiness itemBusiness;

    @GetMapping(value = "/")
    public ResponseEntity<List<ItemDetailResponse>> findProductAll() {
        return ResponseEntity.ok(itemBusiness.findItemAll());
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ItemDetailResponse>> findProductFilter(@RequestParam String name) {
        return ResponseEntity.ok(itemBusiness.findAllByItemNameLike(name));
    }

    @GetMapping(value = "/{itemId}")
    public ResponseEntity<ItemFullDetailResponse> findItemOneByItemId(@PathVariable long itemId) {
        return ResponseEntity.ok(itemBusiness.findItemOneById(itemId));
    }
}