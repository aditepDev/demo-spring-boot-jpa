package com.example.demo.member.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "member_type")
@Setter
@Getter
@RequiredArgsConstructor
public class MemberType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberTypeId;
    private String memberTypeUuid = UUID.randomUUID().toString();

    private String memberTypeName;
    private double salary;

}
