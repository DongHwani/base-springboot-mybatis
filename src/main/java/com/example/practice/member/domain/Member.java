package com.example.practice.member.domain;


import com.example.practice.order.domain.Address;
import lombok.*;

@Getter
@Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class Member {

    private Long memberSequence;
    private String memberId;
    private String password;
    private String phoneNumber;
    private Address address;

}
