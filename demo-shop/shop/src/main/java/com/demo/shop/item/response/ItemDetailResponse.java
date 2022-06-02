package com.demo.shop.item.response;

import com.demo.shop.item.model.Item;
import com.demo.shop.item.model.ItemImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDetailResponse {

    @JsonProperty("item_id")
    private long itemId;
    @JsonProperty("item_name")
    private String itemName;
    @JsonProperty("item_description")
    private String itemDetail;
    @JsonProperty("item_price")
    private double itemPrice;
    @JsonProperty("item_image")
    private String itemImage;


    public static ItemDetailResponse packItemDetail(Item item) {
        ItemDetailResponse itemDetail = new ItemDetailResponse();
        itemDetail.setItemDetail(item.getItemDetail());
        itemDetail.setItemId(item.getItemId());

        String itemItem = null;
        if (!item.getItemImage().isEmpty()) {
            itemItem = item.getItemImage().stream().map(ItemImage::getItemImage).findFirst().get();
        }

        itemDetail.setItemImage(itemItem);
        itemDetail.setItemPrice(item.getItemPrice());
        itemDetail.setItemName(item.getItemName());
        return itemDetail;
    }

    public static List<ItemDetailResponse> packItemDetail(List<Item> items) {
        List<ItemDetailResponse> itemDetailResponses = new ArrayList<>();
        for (Item item : items) {
            itemDetailResponses.add(packItemDetail(item));
        }
        return itemDetailResponses;
    }
}
