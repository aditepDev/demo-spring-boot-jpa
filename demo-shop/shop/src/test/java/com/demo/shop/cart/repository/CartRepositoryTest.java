package com.demo.shop.cart.repository;

import com.demo.shop.cart.model.Cart;
import com.demo.shop.item.model.Item;
import com.demo.shop.member.model.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.demo.shop.mork.MockData;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Test
    @DisplayName("เช็คสินค้าว่ามีในตะกร้ารึยัง")
    void findOneByMemberAndItem() {
        // Arrange
        Cart cart = MockData.getCart();
        cartRepository.save(cart);
        // ACt
        Member member = MockData.getMember();
        Item item = MockData.getItem();
        Optional<Cart>  result =  cartRepository.findOneByMemberAndItem(member,item);
        // Assert
        assertTrue(result.isPresent());
    }



    @Test
    @DisplayName("ดึงข้อมูลสินค้าในตะกร้า")
    void findAllByMember() {
        // Arrange
        Cart cart = MockData.getCart();
        cartRepository.save(cart);
        // ACt
        Member member = MockData.getMember();
        List<Cart>  result =  cartRepository.findAllByMember(member);
        // Assert
        assertEquals(1,result.size());
    }
}