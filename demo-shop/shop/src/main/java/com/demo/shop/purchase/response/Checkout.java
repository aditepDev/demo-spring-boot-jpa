package com.demo.shop.purchase.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Checkout{
	@JsonProperty("total_price")
	private double totalPrice;
	@JsonProperty("total_qty")
	private int totalQty;
}