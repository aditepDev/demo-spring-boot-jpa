package com.demo.shop.purchase.requests;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckOutRequest{
	@JsonProperty("user_detail")
	private UserDetail userDetail;
	@JsonProperty("item_list")
	private List<ItemList> itemList;
}