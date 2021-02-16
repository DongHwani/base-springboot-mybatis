package com.example.practice.member.domain;

import com.example.practice.order.domain.Address;
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
                .address(Address.builder().zipCode("010-555").detailAddress("상세").address("주소").build())
                .build();

        //When
        memberRepository.save(member);

        //Then
        assertThat(member).isNotNull();
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
