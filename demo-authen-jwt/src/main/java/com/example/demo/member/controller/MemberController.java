package com.example.demo.member.controller;

import com.example.demo.member.exception.MemberException;
import com.example.demo.member.response.MemberDetailJson;
import com.example.demo.security.service.JwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    JwtAuthService jwtAuthService;

    @GetMapping("")
    public ResponseEntity<MemberDetailJson> findMember() throws MemberException {
        return new ResponseEntity<>(MemberDetailJson.packMemerDetailJson(jwtAuthService.getCurrentMember()), HttpStatus.OK);
    }
}
