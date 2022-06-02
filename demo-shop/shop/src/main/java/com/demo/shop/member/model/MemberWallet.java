package com.demo.shop.member.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "member_wallet")
public class MemberWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberWalletId;
    private double wallet;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "member_id")
    private Member member;
}
