package com.example.demo.member.service;

import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberType;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.MemberTypeRepository;
import com.example.demo.security.request.RegisterPayload;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Setter
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberTypeRepository memberTypeRepository;

    @Autowired
    private Clock clock;


    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Member findMemberByUuid(String uuid) throws MemberException {
        return memberRepository.findOneByMemberUuid(uuid, Member.class).orElseThrow(MemberException::uuidEmpty);
    }
    public Optional<Member> findMemberByUserName(String username)  {
        return memberRepository.findOneByUsername(username,Member.class);
    }
    public List<MemberType> findAllMemberType() {
        return memberTypeRepository.findAll();
    }

    public String generateMemberCode(RegisterPayload registerPayload) {
        String data = LocalDate.now(clock).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String number = registerPayload.getPhone().substring(registerPayload.getPhone().length() - 4);
        return String.format("%s%s", data, number);
    }


}
