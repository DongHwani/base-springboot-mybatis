package com.example.practice.user;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(exclude = "sequence")
public class User {

    private Long sequence;
    private String userId;
    private String password;
    private String nickName;



}
