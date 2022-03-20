package com.ssafy.a605.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private char gender;
    private String birth;
    private int point;

    public UserDto(String email, String password, String name, String phoneNumber, char gender, String birth, int point) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birth = birth;
        this.point = point;
    }
}
