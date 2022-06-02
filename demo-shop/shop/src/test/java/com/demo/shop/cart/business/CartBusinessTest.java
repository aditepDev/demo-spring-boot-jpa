package com.demo.shop.cart.business;

import com.demo.shop.cart.model.Cart;
import com.demo.shop.cart.repository.CartRepository;
import com.demo.shop.cart.requests.CartPayload;
import com.demo.shop.cart.response.CartResponse;
import com.demo.shop.cart.service.CartService;
import com.demo.shop.exception.BaseException;
import com.demo.shop.item.model.Item;
import com.demo.shop.item.repository.ItemRepository;
import com.demo.shop.item.service.ItemService;
import com.demo.shop.member.model.Member;
import com.demo.shop.mork.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CartBusinessTest {


    @Mock
    private CartRepository cartRepository;
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;
    @InjectMocks
    CartService cartService;
    @InjectMocks
    CartBusiness cartBusiness;

    @BeforeEach
    void setup() {

        itemService.setItemRepository(itemRepository);
        cartService.setCartRepository(cartRepository);
        cartBusiness.setCartService(cartService);
        cartBusiness.setItemService(itemService);
    }

    @Test
    @DisplayName("เพิ่มสินค้าลงตะกร้า")
    void addItemCart() throws Exception {

        // Arrange
        Member member = MockData.getMember();
        CartPayload cartPayload = new CartPayload();
        cartPayload.setItemId(1L);
        cartPayload.setQty(5);

        Item item = MockData.getItem();
        Cart cart = new Cart();




        // check สินค้าว่ามีในระบบไหม
        when(itemService.findOneById(cartPayload.getItemId())).thenReturn(Optional.of(item));
        // check สินค้าว่ามีในตะกร้ารึยัง
        when(cartRepository.findOneByMemberAndItem(member, item)).thenReturn(Optional.empty());

        cart.setItem(item);
        cart.setQty(cartPayload.getQty());
        cart.setMember(member);
        // add สินค้าในตะกร้า
        when(cartService.saveCart(cart)).thenReturn(cart);


        // Act
        Cart result = cartBusiness.updateItemCart(cartPayload, member);
        // Assert
        assertEquals(1, result.getItem().getItemId());
        assertEquals(5, result.getQty());

    }

    @Test
    @DisplayName("update จำนวนสินค้าที่เพิ่มไปแล้ว")
    void updateItemCart() throws Exception {
        // Arrange
        Member member = MockData.getMember();
        CartPayload cartPayload = new CartPayload();
        cartPayload.setItemId(1L);
        cartPayload.setQty(5);

        Item item = MockData.getItem();
        Cart cart = MockData.getCart();




        // check สินค้าว่ามีในระบบไหม
        when(itemService.findOneById(cartPayload.getItemId())).thenReturn(Optional.of(item));
        // check สินค้าว่ามีในตะกร้ารึยัง
        when(cartRepository.findOneByMemberAndItem(member, item)).thenReturn(Optional.of(cart));
        cart.setItem(item);
        cart.setQty(5);

        // Act
        // update จำนวนสินค้าในตะกร้า
        when(cartService.saveCart(cart)).thenReturn(cart);


        Cart result = cartBusiness.updateItemCart(cartPayload, member);
        // Assert
        assertEquals(1, result.getCartId());
        assertEquals(5, result.getQty());
        assertEquals(1, result.getItem().getItemId());

    }

    @Test
    @DisplayName("ลบสินค้าที่จำนวนสินค้าเหลือ 0")
    void removeItemCart() throws Exception {
        // Arrange
        Member member = MockData.getMember();
        CartPayload cartPayload = new CartPayload();
        cartPayload.setItemId(1L);
        cartPayload.setQty(0);
        Item item = MockData.getItem();
        Cart cart = MockData.getCart();

        // check สินค้าว่ามีในระบบไหม
        when(itemService.findOneById(cartPayload.getItemId())).thenReturn(Optional.of(item));
        // check สินค้าว่ามีในตะกร้ารึยัง
        when(cartRepository.findOneByMemberAndItem(member, item)).thenReturn(Optional.of(cart));

        cart.setItem(item);
        cart.setQty(cartPayload.getQty());

        // Act
        // delete สินค้าในตะกร้า
        cartBusiness.updateItemCart(cartPayload, member);
        // Assert
        verify(cartRepository, times(1)).delete(eq(cart));
    }

    @Test
    @DisplayName("ไม่มีรหัสสินค้าในระบบให้ thrown 'cart.item.null'")
    void ItemNull() throws BaseException {
        // Arrange
        Member member = MockData.getMember();
        CartPayload cartPayload = new CartPayload();
        cartPayload.setItemId(11L);
        cartPayload.setQty(0);



        // check สินค้าว่ามีในระบบไหม
        when(itemService.findOneById(cartPayload.getItemId())).thenReturn(Optional.empty());

        // Act
        // delete สินค้าในตะกร้า
        Exception thrown = Assertions.assertThrows(BaseException.class, () -> {
            cartBusiness.updateItemCart(cartPayload, member);
        });
        // Assert

        assertEquals("cart.item.null", thrown.getMessage());
    }

    @Test
    @DisplayName("เช็คถ้าเพิ่มจำนวนสินค้าเข้าตะกร้าติดลบ thrown 'cart.qty.error' ")
    void qtyError() throws BaseException {
        // Arrange
        CartPayload cartPayload = new CartPayload();
        cartPayload.setItemId(11L);
        cartPayload.setQty(-1);

        // Act
        Exception thrown = Assertions.assertThrows(BaseException.class, cartPayload::validate);
        // Assert
        assertEquals("cart.qty.error", thrown.getMessage());
    }

    @Test
    @DisplayName("เช็คถ้าเพิ่มรหัสสินค้าเข้าตะกร้าติดลบ thrown 'cart.itemId.error' ")
    void iditemError() throws BaseException {
        // Arrange
        CartPayload cartPayload = new CartPayload();
        cartPayload.setItemId(0);
        cartPayload.setQty(11);

        // Act
        Exception thrown = Assertions.assertThrows(BaseException.class, cartPayload::validate);
        // Assert
        assertEquals("cart.itemId.error", thrown.getMessage());
    }
    @Test
    @DisplayName("ดึงสินค้าในตะกร้าพร้อมทั้งคำนวณราคา")
    void findItemCart() {
        // Arrange
        Member member = MockData.getMember();
        List<Cart> carts = MockData.getCarts();

        when(cartService.findCartByMember(member)).thenReturn(carts);
        // Act

        CartResponse result = cartBusiness.findItemCart(member);
        // Assert
        assertEquals(30, result.getCheckout().getTotalPrice());
        assertEquals(3, result.getCheckout().getTotalQty());
        assertEquals(5000, result.getPayment().getWallet());
        assertEquals(1, result.getItemList().size());
        assertEquals("Aditep Campira", result.getUserDetail().getUserName());
    }
}