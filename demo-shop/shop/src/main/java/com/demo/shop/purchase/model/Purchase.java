package com.demo.shop.purchase.model;

import com.demo.shop.member.model.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;
    @ManyToOne
    @JoinColumn(name = "member_member_id")
    private Member member;
    private String name;
    private String address;
    private String tel;
    private double total;
    private int qty;
    @Column(name="created_at", columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    private String invoiceNo;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "purchase",cascade = {CascadeType.PERSIST})
    @ToString.Exclude
    private List<PurchaseItem> purchaseItem;
}
