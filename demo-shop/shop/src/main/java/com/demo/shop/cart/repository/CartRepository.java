package com.demo.shop.cart.repository;

import com.demo.shop.cart.model.Cart;
import com.demo.shop.item.model.Item;
import com.demo.shop.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart>  findOneByMemberAndItem(Member member, Item item);

    List<Cart> findAllByMember(Member member);
}
