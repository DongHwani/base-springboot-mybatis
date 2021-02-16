package com.example.practice.order.domain;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    private String address;
    private String detailAddress;
    private String zipCode;
}