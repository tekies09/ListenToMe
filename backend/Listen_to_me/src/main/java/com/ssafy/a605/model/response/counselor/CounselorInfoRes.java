package com.ssafy.a605.model.response.counselor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CounselorInfoRes {
    private String email;
    private String name;
    private String phoneNumber;
    private char gender;
    private String birth;
    private int point;
    private String photo;
    private String shortGreeting;
    private String greeting;
    private String degree;
}
