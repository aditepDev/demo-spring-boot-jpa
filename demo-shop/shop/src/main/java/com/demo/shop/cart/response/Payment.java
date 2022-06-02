package com.demo.shop.cart.response;

import com.demo.shop.member.model.Member;
import lombok.Data;

@Data
public class Payment{
	private double wallet;

	public static Payment packPayment(Member member){
		Payment payment = new Payment();

		payment.setWallet(member.getMemberWallet().getWallet());

		return payment;
	}
}