package com.example.demo.security.business;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberType;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.exception.AuthenException;
import com.example.demo.security.exception.JwtExceptions;
import com.example.demo.security.exception.RegisterException;
import com.example.demo.security.model.CustomUserDetails;
import com.example.demo.security.request.RegisterPayload;
import com.example.demo.member.response.MemberDetailJson;
import com.example.demo.security.service.AuthenticationService;
import com.example.demo.security.service.CustomUserDetailsService;
import com.example.demo.security.service.JwtUtilService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Service
@Setter
@Transactional(rollbackFor = {Exception.class})
public class AuthenticationBusiness {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberService memberService;
    @Autowired
    CustomUserDetailsService userDetailsService;
    @Autowired
    JwtUtilService jwtUtilService;

    public Member register(RegisterPayload registerPayload) throws RegisterException {
        authenticationService.validateRegister(registerPayload);
        String password = passwordEncoder.encode(registerPayload.getPassword());
        MemberType memberType = authenticationService.classifyMemberType(registerPayload.getSalary());
        String code = memberService.generateMemberCode(registerPayload);
        Member member = Member.packMember(registerPayload, password, code, memberType);
        return memberService.saveMember(member);
    }

    public MemberDetailJson login(String username, String password) throws AuthenException, JwtExceptions {

        authenticationService.authenticate(username, password);
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String accessToken = jwtUtilService.generateToken(userDetails.getUser().getMemberUuid());
        String refreshToken = jwtUtilService.generateRefreshToken(userDetails.getUser().getMemberUuid());
        return MemberDetailJson.packMemerDetailJson(userDetails, accessToken, refreshToken);

    }

    public MemberDetailJson refreshToken(HttpServletRequest request) throws JwtExceptions {
        String authToken = request.getHeader("Authorization");

        if(authToken == null) {
            throw JwtExceptions.jwtEmpty();
        }

        String[] authTokenTemp = authToken.split(" ");

        if(authTokenTemp.length < 2) {
            throw JwtExceptions.jwtBearerMissing();
        }

        authToken = authTokenTemp[1];

        MemberDetailJson memberDetailJson = new MemberDetailJson();

        if (StringUtils.hasText(authToken)) {

            String memberUUID = jwtUtilService.extractUserUuid(authToken);
            String accessJwtToken = jwtUtilService.generateToken(memberUUID);

            memberDetailJson.setAccessToken(accessJwtToken);
            memberDetailJson.setRefreshToken(authToken);
        }

        return memberDetailJson;
    }
}
