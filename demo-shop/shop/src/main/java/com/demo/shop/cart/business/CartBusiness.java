package com.demo.shop.cart.business;

import com.demo.shop.cart.exception.CartException;
import com.demo.shop.cart.model.Cart;
import com.demo.shop.cart.requests.CartPayload;
import com.demo.shop.cart.response.CartResponse;
import com.demo.shop.cart.service.CartService;
import com.demo.shop.item.model.Item;
import com.demo.shop.item.service.ItemService;
import com.demo.shop.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class CartBusiness {

    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    public Cart updateItemCart(CartPayload cartPayload, Member member) throws CartException {
        Optional<Item> item = itemService.findOneById(cartPayload.getItemId());
        return   addItemToCart(member,item,cartPayload.getQty());
    }

    private Cart addItemToCart(Member member,Optional<Item> item,int qty) throws CartException {

        Cart cart;
        if (item.isPresent()) {
            cart = cartService.checkCartByMemberAndItem(member, item.get());

            cart.setItem(item.get());
            cart.setQty(qty);
            cart.setMember(member);

            if (cart.getCartId() != 0 && qty == 0) {
                cartService.deleteItemOnCart(cart);
            } else if (qty != 0) {
                return cartService.saveCart(cart);
            }
        } else {
            throw CartException.itemNull();
        }
        return cart;
    }

    public CartResponse findItemCart(Member member) {
        List<Cart> carts = cartService.findCartByMember(member);
        return CartResponse.packCartResponse(carts, member);
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
