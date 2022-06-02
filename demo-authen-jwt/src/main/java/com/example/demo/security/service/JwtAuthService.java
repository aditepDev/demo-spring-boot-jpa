package com.example.demo.security.service;

import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Member;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.model.UserAuth;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Setter
public class JwtAuthService {

    @Autowired
    MemberService memberService;

    public UserAuth getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserAuth) authentication.getCredentials();
    }

    public Member getCurrentMember() throws MemberException {
        return memberService.findMemberByUuid(getCurrentUser().getMemberUuid());
    }

}
