package com.ssafy.a605.model.entity;

import com.ssafy.a605.model.dto.UserDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@NoArgsConstructor //기본생성자 생성
@Entity
@Data
@Getter
public class User {

    @Id
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private char gender;
    private String birth;
    private int point;


    public User(String email, String password, String name, String phoneNumber, char gender, String birth, int point) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birth = birth;
        this.point = point;
    }

    public void update(UserDto userDto){
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.phoneNumber = userDto.getPhoneNumber();
        this.gender = userDto.getGender();
        this.birth = userDto.getBirth();
        this.point = userDto.getPoint();
    }
}
