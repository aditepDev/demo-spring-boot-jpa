package com.demo.shop.purchase.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillDetail{
	@JsonProperty("bill_date")
	private LocalDateTime billDate;
	@JsonProperty("bill_invoice_no")
	private String billInvoiceNo;
	@JsonProperty("bill_name")
	private String billName;
}