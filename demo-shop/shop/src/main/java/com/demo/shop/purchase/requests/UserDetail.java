package com.demo.shop.purchase.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDetail{
	@JsonProperty("user_tel")
	private String userTel;
	@JsonProperty("user_name")
	private String userName;
	@JsonProperty("user_address")
	private String userAddress;
}