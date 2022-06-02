package com.example.demo.security.service;

import com.example.demo.member.model.MemberType;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.exception.AuthenException;
import com.example.demo.security.exception.RegisterException;
import com.example.demo.security.request.RegisterPayload;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Setter
public class AuthenticationService {
    @Autowired
    MemberService memberService;
    @Autowired
    AuthenticationManager authenticationManager;

    public MemberType classifyMemberType(double salary) throws RegisterException {
        return memberService.findAllMemberType().stream().
                filter(type -> type.getSalary() < salary).findAny().orElseThrow(RegisterException::lowRate);
    }


    public void authenticate(String username, String password) throws AuthenException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw AuthenException.authenIncorrect();
        }
    }

    public void validateRegister(RegisterPayload registerPayload) throws RegisterException {
        if(registerPayload.getUsername().length() == 0) {
            throw RegisterException.usernameIsEmpty();
        }
        if(registerPayload.getPassword().length() == 0) {
            throw RegisterException.passwordIsEmpty();
        }
        if(memberService.findMemberByUserName(registerPayload.getUsername()).isPresent()){
            throw RegisterException.usernameDuplicate();
        }
        if(Objects.isNull(registerPayload.getPhone()) || registerPayload.getPhone().length() < 10){
            throw RegisterException.phoneNumberIncorrect();
        }
    }
}
