package com.example.demo.member.response;

import com.example.demo.member.model.Member;
import com.example.demo.security.model.CustomUserDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MemberDetailJson {
    private String memberType;
    private String accessToken;
    private String refreshToken;
    private String address;
    private String phone;
    private String memberCode;
    private String name;
    private double salary;

    public static MemberDetailJson packMemerDetailJson(CustomUserDetails userDetails, String accessToken, String refreshToken) {
        MemberDetailJson memberDetailJson = new MemberDetailJson();
        memberDetailJson.setMemberType(userDetails.getUser().getMemberType().getMemberTypeName());
        memberDetailJson.setAccessToken(accessToken);
        memberDetailJson.setRefreshToken(refreshToken);
        memberDetailJson.setAddress(userDetails.getUser().getAddress());
        memberDetailJson.setPhone(userDetails.getUser().getPhone());
        memberDetailJson.setMemberCode(userDetails.getUser().getMemberCode());
        memberDetailJson.setName(userDetails.getUser().getName());
        memberDetailJson.setSalary(userDetails.getUser().getSalary());

        return memberDetailJson;
    }
    public static MemberDetailJson packMemerDetailJson(Member member) {
        MemberDetailJson memberDetailJson = new MemberDetailJson();
        memberDetailJson.setMemberType(member.getMemberType().getMemberTypeName());
        memberDetailJson.setAddress(member.getAddress());
        memberDetailJson.setPhone(member.getPhone());
        memberDetailJson.setMemberCode(member.getMemberCode());
        memberDetailJson.setName(String.format("%s %s",member.getFirstName(),member.getLastName()));
        memberDetailJson.setSalary(member.getSalary());

        return memberDetailJson;
    }
}