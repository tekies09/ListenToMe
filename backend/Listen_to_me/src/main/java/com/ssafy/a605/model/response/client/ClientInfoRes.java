package com.ssafy.a605.model.response.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientInfoRes {
    private String email;
    private String name;
    private String phoneNumber;
    private char gender;
    private String birth;
    private int point;
    private String nickname;
}
