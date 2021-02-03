package com.example.practice.member.application;

import com.example.practice.member.domain.Member;
import com.example.practice.member.interfaces.exception.DuplicateMemberIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void save() {
        //Given
        Member member = Member.builder()
                .memberId("user2")
                .password("pass")
                .build();

        //When
        Member savedMember = memberService.save(member);

        //Then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    public void duplicateMemberIdException(){
        assertThatThrownBy(() ->
            memberService.save(Member.builder().memberId("user2").build())
        ).isInstanceOf(DuplicateMemberIdException.class);
    }
}
