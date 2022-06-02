package com.demo.shop.item.response;

import com.demo.shop.item.model.Item;
import com.demo.shop.item.model.ItemImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class ItemFullDetailResponse {

    @JsonProperty("item_id")
    private long itemId;
    @JsonProperty("item_name")
    private String itemName;
    @JsonProperty("item_description")
    private String itemDetail;
    @JsonProperty("item_price")
    private double itemPrice;
    @JsonProperty("item_image")
    private List<String> itemImage;
    @JsonProperty("item_rating")
    private float ItemRating;

    public static ItemFullDetailResponse packItemFullDetail(Item item) {
        if (Objects.nonNull(item)) {
            ItemFullDetailResponse itemDetail = new ItemFullDetailResponse();
            itemDetail.setItemDetail(item.getItemDetail());
            itemDetail.setItemId(item.getItemId());
            itemDetail.setItemImage(item.getItemImage().stream().map(ItemImage::getItemImage).collect(Collectors.toList()));
            itemDetail.setItemPrice(item.getItemPrice());
            itemDetail.setItemName(item.getItemName());
            itemDetail.setItemRating(item.getItemRating());
            return itemDetail;
        }
        return new ItemFullDetailResponse();
    }

    public static ItemFullDetailResponse packItemFullDetail(Optional<Item> item) {

        if (item.isPresent()) {
            return ItemFullDetailResponse.packItemFullDetail(item.get());
        }
        return new ItemFullDetailResponse();
    }


}
