package com.demo.shop.cart.response;

import com.demo.shop.cart.model.Cart;
import com.demo.shop.item.model.Item;
import com.demo.shop.item.model.ItemImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemList {
	@JsonProperty("item_rating")
	private double itemRating;
	@JsonProperty("item_id")
	private long itemId;
	@JsonProperty("item_price")
	private double itemPrice;
	private int qty;
	@JsonProperty("item_image")
	private String itemImage;
	@JsonProperty("item_name")
	private String itemName;
	@JsonProperty("item_description")
	private String itemDescription;


	public static ItemList packItemList(Cart cart) {
		ItemList itemList = new ItemList();
		Item item = cart.getItem();
		itemList.setItemId(item.getItemId());
		itemList.setItemName(item.getItemName());
		String itemItem = null;
		if (!item.getItemImage().isEmpty()) {
			itemItem = item.getItemImage().stream().map(ItemImage::getItemImage).findFirst().get();
		}
		itemList.setItemImage(itemItem);
		itemList.setItemDescription(item.getItemDetail());
		itemList.setItemPrice(item.getItemPrice());
		itemList.setItemRating(Double.parseDouble(new DecimalFormat("0.00").format(item.getItemRating())));
		itemList.setQty(cart.getQty());
		return itemList;
	}

	public static List<ItemList> packItemList(List<Cart> carts) {
		List<ItemList> itemLists = new ArrayList<>();
		for (Cart cart:carts) {
			itemLists.add(packItemList(cart));
		}
		return itemLists;
	}
}
