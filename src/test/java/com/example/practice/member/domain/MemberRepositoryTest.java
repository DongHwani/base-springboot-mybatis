package com.example.practice.member.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void save(){
        //Given
        Member member = Member.builder()
                .memberId("user")
                .password("user")
                .phoneNumber("010")
                .build();

        //When
        int savedUser = memberRepository.save(member);

        //Then
        assertThat(savedUser).isEqualTo(1);
    }

    @Test
    public void findByMemberId(){
        //Given
        Member member = Member.builder()
                .memberId("user")
                .password("user")
                .build();
        //When
        Member findMember = memberRepository.findByMemberId(member.getMemberId()).get();

        //Then
        assertThat(member).isEqualTo(findMember);
    }
}
