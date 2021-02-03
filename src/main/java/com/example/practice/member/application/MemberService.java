package com.example.practice.member.application;

import com.example.practice.member.domain.Member;
import com.example.practice.member.domain.MemberRepository;
import com.example.practice.member.interfaces.exception.DuplicateMemberIdException;
import com.example.practice.member.interfaces.exception.NotMatchedMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    public Member save(Member member) {
        validateDuplicateMemberId(member);

        memberRepository.save(member);

        return member;
    }

    private void validateDuplicateMemberId(Member member) {
        if(memberRepository.existMember(member.getMemberId())) {
            throw new DuplicateMemberIdException();
        }
    }

    public Member login(Member member) {
        Member loginMember = memberRepository.findByMemberId(member.getMemberId())
                                    .filter((m) -> m.getPassword().equals(member.getPassword()))
                                    .orElseThrow(() -> new NotMatchedMemberException());
        return loginMember;
    }
}
