package com.demo.shop.member.repository;

import com.demo.shop.member.model.MemberWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberWalletRepository extends JpaRepository<MemberWallet,Long> {
}
