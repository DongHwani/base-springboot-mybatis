package com.example.practice.member.interfaces;

import com.example.practice.member.application.MemberService;
import com.example.practice.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity register(@RequestBody Member member) {
        Member savedMember = memberService.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }
}