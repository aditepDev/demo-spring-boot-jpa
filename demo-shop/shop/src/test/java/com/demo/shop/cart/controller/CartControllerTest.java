package com.demo.shop.cart.controller;

import com.demo.shop.cart.model.Cart;
import com.demo.shop.cart.repository.CartRepository;
import com.demo.shop.cart.requests.CartPayload;
import com.demo.shop.cart.response.CartResponse;
import com.demo.shop.item.model.Item;
import com.demo.shop.item.repository.ItemRepository;
import com.demo.shop.item.response.ItemDetailResponse;
import com.demo.shop.member.model.Member;
import com.demo.shop.mork.MockData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {
    @MockBean
    private CartRepository cartRepository;
    @MockBean
    private ItemRepository itemRepository;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("เพิ่มสินค้าลงตะกร้า")
    void updateItemCart() {
        // Arrange
        Member member = MockData.getMember();
        CartPayload cartPayload = new CartPayload();
        cartPayload.setItemId(1L);
        cartPayload.setQty(5);

        Item item = MockData.getItem();
        Cart cart = new Cart();

        // check สินค้าว่ามีในระบบไหม
        when(itemRepository.findById(cartPayload.getItemId())).thenReturn(Optional.of(item));
        // check สินค้าว่ามีในตะกร้ารึยัง
        when(cartRepository.findOneByMemberAndItem(member, item)).thenReturn(Optional.empty());

        cart.setItem(item);
        cart.setQty(cartPayload.getQty());
        cart.setMember(member);
        // add สินค้าในตะกร้า
        when(cartRepository.save(cart)).thenReturn(cart);
        // Act
        ResponseEntity result = testRestTemplate.postForEntity("/cart",cartPayload,ResponseEntity.class);
        // assert
        assertEquals(201,result.getStatusCodeValue());

    }

    @Test
    @DisplayName("ดึงข้อมูลตะกร้าสินค้า")
    void findItemCart() {
        // Arrange
        Member member = MockData.getMember();
        List<Cart> carts = MockData.getCarts();
        when(cartRepository.findAllByMember(member)).thenReturn(carts);
        // Act
        CartResponse result = testRestTemplate.getForObject("/cart", CartResponse.class);
        // assert
        assertEquals(5000, result.getPayment().getWallet());
        assertEquals("Aditep Campira", result.getUserDetail().getUserName());
    }
}