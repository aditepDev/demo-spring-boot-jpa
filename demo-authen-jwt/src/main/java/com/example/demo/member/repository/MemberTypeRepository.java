package com.example.demo.member.repository;

import com.example.demo.member.model.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTypeRepository extends JpaRepository<MemberType,Long> {
}
