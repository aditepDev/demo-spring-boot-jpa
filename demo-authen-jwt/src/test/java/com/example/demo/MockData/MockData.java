package com.example.demo.MockData;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberType;
import com.example.demo.security.request.RegisterPayload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static final LocalDateTime DATE_MOCK = LocalDateTime.parse("2022-02-22 22:22:22", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    public static final String PASSWORD_MOCK = "1234";
    public  static final LocalDate LOCAL_DATE = LocalDate.of(2022, 02, 02);

    public static List<MemberType> getMemberTypes() {
        List<MemberType> memberTypes = new ArrayList<>();
        memberTypes.add(getMemberTypePlatinum());
        memberTypes.add(getMemberTypeGold());
        memberTypes.add(getMemberTypeSilver());
        return memberTypes;
    }

    public static  MemberType getMemberTypePlatinum() {
        MemberType memberType = new MemberType();
        memberType.setMemberTypeId(1);
        memberType.setMemberTypeUuid("uuid");
        memberType.setMemberTypeName("Platinum");
        memberType.setSalary(50000);
        return memberType;
    }

    public static MemberType getMemberTypeGold() {
        MemberType memberType = new MemberType();
        memberType.setMemberTypeId(2);
        memberType.setMemberTypeUuid("uuid");
        memberType.setMemberTypeName("Gold");
        memberType.setSalary(30000);
        return memberType;

    }

    public static MemberType getMemberTypeSilver() {
        MemberType memberType = new MemberType();
        memberType.setMemberTypeId(3);
        memberType.setMemberTypeUuid("uuid");
        memberType.setMemberTypeName("Silver");
        memberType.setSalary(15000);
        return memberType;
    }

    public static Member getMemberPlatinum() {
        Member member = new Member();
        member.setMemberId(1);
        member.setMemberUuid("uuid");
        member.setMemberCode("202205313293");
        member.setUsername("userPlatinum");
        member.setPassword("$2a$10$dOq.2OCg5vHqrkImJyPXluvl7Jkw.uxP59/OI.m79ceEeHP6n6Wku");
        member.setFirstName("aditep");
        member.setLastName("campira");
        member.setAddress("udonthani");
        member.setPhone("0801953293");
        member.setSalary(50001);
        member.setMemberType(getMemberTypePlatinum());
        member.setCreatedAt(DATE_MOCK);
        member.setUpdatedAt(DATE_MOCK);
        return member;
    }

    public static Member getMemberGold() {
        Member member = new Member();
        member.setMemberId(2);
        member.setMemberUuid("uuid");
        member.setMemberCode("202205313293");
        member.setUsername("userGold");
        member.setPassword("$2a$10$dOq.2OCg5vHqrkImJyPXluvl7Jkw.uxP59/OI.m79ceEeHP6n6Wku");
        member.setFirstName("wichi");
        member.setLastName("khondi");
        member.setAddress("93 m.3");
        member.setPhone("0801234999");
        member.setSalary(30001);
        member.setMemberType(getMemberTypePlatinum());
        member.setCreatedAt(DATE_MOCK);
        member.setUpdatedAt(DATE_MOCK);
        return member;
    }

    public static Member getMemberSilver() {
        Member member = new Member();
        member.setMemberId(3);
        member.setMemberUuid("uuid");
        member.setMemberCode("202205313293");
        member.setUsername("userSilver");
        member.setPassword("$2a$10$dOq.2OCg5vHqrkImJyPXluvl7Jkw.uxP59/OI.m79ceEeHP6n6Wku");
        member.setFirstName("sodsi");
        member.setLastName("jide");
        member.setAddress("bkk");
        member.setPhone("0801234567");
        member.setSalary(15001);
        member.setMemberType(getMemberTypePlatinum());
        member.setCreatedAt(DATE_MOCK);
        member.setUpdatedAt(DATE_MOCK);
        return member;
    }

    public  static  RegisterPayload getRegisterPayloadPlatinum(){
        RegisterPayload registerPayload = new RegisterPayload();
        registerPayload.setUsername("userPlatinum");
        registerPayload.setPassword(PASSWORD_MOCK);
        registerPayload.setFirstName("aditep");
        registerPayload.setLastName("campira");
        registerPayload.setSalary(50001);
        registerPayload.setPhone("0801953293");
        registerPayload.setAddress("udonthani");
        return registerPayload;
    }

}
