package com.example.demo.member.repository;

import com.example.demo.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    <T> Optional<T> findOneByUsername(String username, Class<T> type);

    <T> Optional<T>  findOneByMemberUuid(String uuid, Class<T> type);

}
