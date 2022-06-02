package com.example.demo.member.service;

import com.example.demo.MockData.MockData;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberType;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.MemberTypeRepository;
import com.example.demo.security.request.RegisterPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberTypeRepository memberTypeRepository;

    @InjectMocks
    MemberService memberService;

    @Mock
    private Clock clock;
    private Clock fixedClock;

    @BeforeEach
    void setup() {
        memberService.setMemberRepository(memberRepository);
        memberService.setMemberTypeRepository(memberTypeRepository);
        memberService.setClock(clock);

    }

    @Test
    @DisplayName("เพิ่มสมาชิกใหม่")
    void saveMember() {
        Member member =  MockData.getMemberPlatinum();
        when(memberRepository.save(Mockito.any(Member.class))).thenAnswer(i -> i.getArguments()[0]);
        Member result = memberService.saveMember(member);
        assertEquals("uuid",result.getMemberUuid());
    }

    @Test
    @DisplayName("ดึงข้อมูลสมาชิกด้วย : uuid")
    void findMemberByUuid() throws MemberException {
        String uuid = "uuid";
        Member member =  MockData.getMemberPlatinum();
        when(memberRepository.findOneByMemberUuid(uuid,Member.class)).thenReturn(Optional.of(member));
        Member result = memberService.findMemberByUuid(uuid);
        assertEquals("uuid",result.getMemberUuid());
    }

    @Test
    @DisplayName("ดึงข้อมูลสมาชิกด้วย : username")
    void findMemberByUserName() {
        String username = "userPlatinum";
        Member member =  MockData.getMemberPlatinum();
        when(memberRepository.findOneByUsername(username,Member.class)).thenReturn(Optional.of(member));
        Optional<Member> result = memberService.findMemberByUserName(username);
        assert(result.isPresent());
        assertEquals("uuid",result.get().getMemberUuid());
        assertEquals("userPlatinum",result.get().getUsername());

    }

    @Test
    @DisplayName("ดึงข้อมูลประเภทสมาชิกทั้งหมด")
    void findAllMemberType() {
        List<MemberType> memberTypes = MockData.getMemberTypes();
        when(memberTypeRepository.findAll()).thenReturn(memberTypes);
        List<MemberType> result = memberService.findAllMemberType();

        assertEquals(3,result.size());
        assertEquals(50000,result.get(0).getSalary());
        assertEquals(30000,result.get(1).getSalary());
        assertEquals(15000,result.get(2).getSalary());

    }

    @Test
    @DisplayName("สร้างรหัส member code")
    void generateMemberCode() {

        fixedClock = Clock.fixed(MockData.LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();

        RegisterPayload registerPayload =  MockData.getRegisterPayloadPlatinum();
        String result = memberService.generateMemberCode(registerPayload);
        assertEquals("202202023293",result);
    }
}