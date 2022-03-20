package com.ssafy.a605.model.dto;

import com.ssafy.a605.model.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto extends UserDto {

    private String nickname;
    public ClientDto(String email, String password, String name, String phoneNumber, char gender, String birth, int point, String nickname){
        super(email, password, name, phoneNumber, gender, birth, point);
        this.nickname= nickname;
    }


}
