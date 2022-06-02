package com.example.demo.security.service;

import com.example.demo.MockData.MockData;
import com.example.demo.member.model.MemberType;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.MemberTypeRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.exception.RegisterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    MemberTypeRepository memberTypeRepository;
    @InjectMocks
    MemberService memberService;
    @InjectMocks
    AuthenticationService authenticationService;
    @BeforeEach
    void setup(){
        memberService.setMemberTypeRepository(memberTypeRepository);
        authenticationService.setMemberService(memberService);
    }
    @Test
    @DisplayName("จัดกลุ่ม member ด้วย salary : Platinum")
    void classifyMemberTypePlatinum() throws RegisterException {
        double salary = 50001;
        List<MemberType> memberTypes = MockData.getMemberTypes();
        when(memberTypeRepository.findAll()).thenReturn(memberTypes);
        MemberType result = authenticationService.classifyMemberType(salary);
        assertEquals(1,result.getMemberTypeId());
        assertEquals("Platinum",result.getMemberTypeName());
    }
    @Test
    @DisplayName("จัดกลุ่ม member ด้วย salary : Gold")
    void classifyMemberTypeGold() throws RegisterException {
        double salary = 30001;
        List<MemberType> memberTypes = MockData.getMemberTypes();
        when(memberTypeRepository.findAll()).thenReturn(memberTypes);
        MemberType result = authenticationService.classifyMemberType(salary);
        assertEquals(2,result.getMemberTypeId());
        assertEquals("Gold",result.getMemberTypeName());
    }
    @Test
    @DisplayName("จัดกลุ่ม member ด้วย salary : Silver")
    void classifyMemberTypeSilver() throws RegisterException {
        double salary = 15001;
        List<MemberType> memberTypes = MockData.getMemberTypes();
        when(memberTypeRepository.findAll()).thenReturn(memberTypes);
        MemberType result = authenticationService.classifyMemberType(salary);
        assertEquals(3,result.getMemberTypeId());
        assertEquals("Silver",result.getMemberTypeName());
    }
    @Test
    @DisplayName("จัดกลุ่ม member ด้วย salary : Error")
    void classifyMemberTypeError() throws RegisterException {
        double salary = 14999;
        List<MemberType> memberTypes = MockData.getMemberTypes();
        when(memberTypeRepository.findAll()).thenReturn(memberTypes);
        RegisterException result = assertThrows(RegisterException.class, () -> authenticationService.classifyMemberType(salary));
        assertEquals(RegisterException.lowRate().getMessage(),result.getMessage());
    }

}