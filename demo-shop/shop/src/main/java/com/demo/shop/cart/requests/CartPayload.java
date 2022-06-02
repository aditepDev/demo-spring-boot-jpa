package com.demo.shop.cart.requests;

import com.demo.shop.cart.exception.CartException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartPayload {
    @JsonProperty("item_id")
    private long itemId;
    private int qty;


    public void validate() throws CartException {

        if(this.itemId <= 0){
        throw CartException.custom("itemId.error");
        }
        if(this.qty < 0){
            throw CartException.custom("qty.error");
        }
    }
}
