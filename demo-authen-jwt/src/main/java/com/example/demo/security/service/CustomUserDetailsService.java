package com.example.demo.security.service;

import com.example.demo.security.model.CustomUserDetails;
import com.example.demo.security.model.UserAuth;
import com.example.demo.member.repository.MemberRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Setter
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    public CustomUserDetails setUserDetails(UserAuth user) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getMemberType().getMemberTypeName()));
        return new CustomUserDetails(user.getMemberUuid(), user.getPassword(), roles, user);
    }

    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = memberRepository.findOneByUsername(username, UserAuth.class)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s, not found", username)));
        return setUserDetails(user);
    }

    public CustomUserDetails loadUserByUuid(String uuid) throws UsernameNotFoundException {
        UserAuth user = memberRepository.findOneByMemberUuid(uuid, UserAuth.class)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("UserUUID: %s, not found", uuid)));
        return setUserDetails(user);
    }

}
