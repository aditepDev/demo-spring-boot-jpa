package com.demo.shop.purchase.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckOutResponse{
	@JsonProperty("bill_detail")
	private BillDetail billDetail;
	private Checkout checkout;
}