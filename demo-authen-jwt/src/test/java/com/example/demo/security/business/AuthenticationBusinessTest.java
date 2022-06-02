package com.example.demo.security.business;

import com.example.demo.MockData.MockData;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberType;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.MemberTypeRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.exception.RegisterException;
import com.example.demo.security.request.RegisterPayload;
import com.example.demo.security.service.AuthenticationService;
import io.jsonwebtoken.lang.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationBusinessTest {
    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberTypeRepository memberTypeRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    MemberService memberService;
    @InjectMocks
    AuthenticationService authenticationService;
    @InjectMocks
    AuthenticationBusiness authenticationBusiness;

    @BeforeEach
    void setup(){
        memberService.setMemberRepository(memberRepository);
        memberService.setMemberTypeRepository(memberTypeRepository);
        authenticationService.setMemberService(memberService);
        authenticationBusiness.setAuthenticationService(authenticationService);
        authenticationBusiness.setPasswordEncoder(passwordEncoder);
    }

    @Test
    @DisplayName("เพิ่มสมาชิกใหม่แล้ว user ซ้ำ")
    void registerUsernameDuplicate() {
        RegisterPayload registerPayload = MockData.getRegisterPayloadPlatinum();

        when( memberRepository.findOneByUsername(registerPayload.getUsername(), Member.class)).thenReturn(Optional.of(MockData.getMemberPlatinum()));
        RegisterException result = assertThrows(RegisterException.class, () -> authenticationBusiness.register(registerPayload));
        assertEquals(RegisterException.usernameDuplicate().getMessage(),result.getMessage());
    }

}