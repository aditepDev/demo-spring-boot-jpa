package com.demo.shop.cart.service;

import com.demo.shop.cart.model.Cart;
import com.demo.shop.cart.repository.CartRepository;
import com.demo.shop.item.model.Item;
import com.demo.shop.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
    public void deleteItemOnCart(Cart cart) {
         cartRepository.delete(cart);
    }
    public Cart checkCartByMemberAndItem(Member member, Item item) {
        Optional<Cart> cart = cartRepository.findOneByMemberAndItem(member, item);
        if (cart.isEmpty()) {
            return new Cart();
        }
        return cart.get();

    }

    public List<Cart> findCartByMember(Member member){
        return cartRepository.findAllByMember(member);
    }

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
