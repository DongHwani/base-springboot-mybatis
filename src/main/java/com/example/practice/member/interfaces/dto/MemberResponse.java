package com.example.practice.member.interfaces.dto;

import com.example.practice.purchase.domain.Address;
import lombok.Data;
import lombok.ToString;

@Data @ToString
public class MemberResponse {

    private Long memberSequence;
    private String memberId;
    private String phoneNumber;
    private Address address;

}
