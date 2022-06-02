package com.demo.shop.mork;

import com.demo.shop.cart.model.Cart;
import com.demo.shop.item.model.Item;
import com.demo.shop.item.model.ItemImage;
import com.demo.shop.member.model.Member;
import com.demo.shop.member.model.MemberWallet;
import com.demo.shop.purchase.model.Purchase;
import com.demo.shop.purchase.model.PurchaseItem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static Member getMember() {
        Member member = new Member();
        member.setMemberId(1);
        member.setMemberName("Aditep Campira");
        member.setMemberTel("080-1953293");
        member.setMemberAddress("93 m.3 Muang Udonthani 41000");
        member.setMemberWallet(getMemberWallet(member));
        return member;
    }

    public static MemberWallet getMemberWallet(Member member) {
        MemberWallet memberWallet = new MemberWallet();
        memberWallet.setWallet(5000);
        memberWallet.setMember(member);
        return memberWallet;
    }

    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(getItem());
        return items;
    }

    public static Item getItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setItemDetail("detail");
        item.setItemName("mame");
        item.setItemPrice(10.0);
        item.setItemRating(4);
        List<ItemImage> itemImages = getItemImages(item);
        item.setItemImage(itemImages);
        return item;
    }

    public static List<ItemImage> getItemImages(Item item) {
        List<ItemImage> itemImages = new ArrayList<>();
        ItemImage itemImage = new ItemImage();
        itemImage.setItemImageId(1);
        itemImage.setItemImage("item.jpg");
        itemImage.setItem(item);
        itemImage.setItemImageId(1);
        itemImages.add(itemImage);
        return itemImages;
    }

    public static List<Cart> getCarts() {
        List<Cart> Carts  = new ArrayList<>();
        Carts.add(getCart());
        return Carts;
    }

    public static Cart getCart() {
        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setMember(MockData.getMember());
        cart.setItem(MockData.getItem());
        cart.setQty(3);
        cart.setCreatedAt(LocalDateTime.parse("2022-03-13 00:01:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return cart;
    }

    public static  Purchase getPurchase(String invoiceNo, Member member, Item item) {
        Purchase purchase = new Purchase();
        purchase.setPurchaseId(1);
        purchase.setInvoiceNo(invoiceNo);
        purchase.setMember(member);
        purchase.setQty(2);
        purchase.setTotal(20);
        purchase.setTel(member.getMemberTel());
        purchase.setAddress(member.getMemberAddress());
        purchase.setName(member.getMemberName());
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setPurchase(purchase);
        purchaseItem.setItem(item);
        purchaseItem.setPurchaseItemId(1);
        purchaseItem.setItemDetail(item.getItemDetail());
        purchaseItem.setPrice(item.getItemPrice());
        purchaseItem.setQty(2);
        purchase.setPurchaseItem(purchaseItems);
        return purchase;
    }
}
