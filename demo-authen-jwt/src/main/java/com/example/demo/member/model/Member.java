package com.example.demo.member.model;

import com.example.demo.security.request.RegisterPayload;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "member")
@Setter
@Getter
@RequiredArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    private String memberUuid = UUID.randomUUID().toString();
    private String memberCode;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private double salary;


    @Column(name="created_at", columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name="updated_at", columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_type_id")
    private MemberType memberType;


    public static Member packMember(RegisterPayload registerPayload,String password,String code,MemberType memberType){
        Member member = new Member();
        member.setMemberCode(code);
        member.setMemberType(memberType);
        member.setAddress(registerPayload.getAddress());
        member.setUsername(registerPayload.getUsername());
        member.setPassword(password);
        member.setFirstName(registerPayload.getFirstName());
        member.setLastName(registerPayload.getLastName());
        member.setSalary(registerPayload.getSalary());
        member.setPhone(registerPayload.getPhone());
        return member;
    }
}
