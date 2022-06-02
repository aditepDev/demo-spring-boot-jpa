package com.example.demo.security.controller;

import com.example.demo.security.business.AuthenticationBusiness;
import com.example.demo.security.exception.AuthenException;
import com.example.demo.security.exception.JwtExceptions;
import com.example.demo.security.exception.RegisterException;
import com.example.demo.security.request.LoginPayload;
import com.example.demo.security.request.RegisterPayload;
import com.example.demo.member.response.MemberDetailJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationBusiness authenticationBusiness;

    @PostMapping("/register")
    public ResponseEntity<MemberDetailJson> register(@RequestBody RegisterPayload registerPayload) throws RegisterException, AuthenException, JwtExceptions {

        authenticationBusiness.register(registerPayload);
        MemberDetailJson memberDetailJson = authenticationBusiness.login(registerPayload.getUsername(), registerPayload.getPassword());

        return new ResponseEntity<>(memberDetailJson, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDetailJson> login(@RequestBody LoginPayload loginPayload) throws AuthenException, JwtExceptions {
        MemberDetailJson memberDetailJson = authenticationBusiness.login(loginPayload.getUsername(), loginPayload.getPassword());
        return new ResponseEntity<>(memberDetailJson, HttpStatus.OK);
    }

    @PostMapping(value = "/refreshToken")
    public ResponseEntity<MemberDetailJson> refreshToken(HttpServletRequest request) throws JwtExceptions {
        MemberDetailJson memberDetailJson = authenticationBusiness.refreshToken(request);
        return ResponseEntity.ok(memberDetailJson);
    }
}
