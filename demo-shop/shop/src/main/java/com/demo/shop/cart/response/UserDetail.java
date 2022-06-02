package com.demo.shop.cart.response;

import com.demo.shop.member.model.Member;
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

	public static UserDetail packUserDetail(Member member){
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName(member.getMemberName());
		userDetail.setUserAddress(member.getMemberAddress());
		userDetail.setUserTel(member.getMemberTel());
		return userDetail;
	}
}