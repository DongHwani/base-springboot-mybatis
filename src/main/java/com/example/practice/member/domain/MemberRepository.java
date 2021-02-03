package com.example.practice.member.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface MemberRepository {

    int save(Member member);

    Optional<Member> findByMemberId(String memberId);

    boolean existMember(String memberId);
}
