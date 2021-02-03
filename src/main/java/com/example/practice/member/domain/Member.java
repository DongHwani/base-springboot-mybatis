package com.example.practice.member.domain;


import com.example.practice.purchase.domain.Address;
import lombok.*;

@Getter
@Builder @ToString
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(exclude ={ "password", "phoneNumber", "address", "detailAddress", "zipCode"})
public class Member {

    private Long memberSequence;
    private String memberId;
    private String password;
    private String phoneNumber;
    private Address address;

}
