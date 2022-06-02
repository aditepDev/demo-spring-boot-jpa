package com.example.demo.security.model;

import com.example.demo.member.model.MemberType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;


@Getter
@Setter
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 8142376270878688021L;

    private final long memberId;
    private final String memberUuid;
    private final String username;
    private final String password;
    private final MemberType memberType;
    private final String address;
    private final String memberCode;
    private final double salary;
    private final String phone;

    @JsonIgnore
    private final String firstName;
    @JsonIgnore
    private final String lastName;

    private String name;

    public UserAuth(long memberId, String memberUuid, String username, String password, MemberType memberType, String address, String memberCode, double salary, String phone, String firstName, String lastName) {
        this.memberId = memberId;
        this.memberUuid = memberUuid;
        this.username = username;
        this.password = password;
        this.memberType = memberType;
        this.address = address;
        this.memberCode = memberCode;
        this.salary = salary;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = String.format("%s %s",firstName,lastName);
    }
}
