package com.ssafy.a605.model.entity;

import com.ssafy.a605.model.dto.CounselorDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@NoArgsConstructor //기본생성자 생성
@DiscriminatorValue("Counselor")
@Entity
@Data
@Getter
public class Counselor extends User{

    private String photo;
    private String shortGreeting;
    private String greeting;
    private String degree;

    public Counselor(String email, String password, String name, String phoneNumber, char gender, String birth, int point, String photo, String shortGreeting, String greeting, String degree) {
        super(email, password, name, phoneNumber, gender, birth, point);
        this.photo = photo;
        this.shortGreeting = shortGreeting;
        this.greeting = greeting;
        this.degree = degree;
    }

    public void update(CounselorDto counselorDto){
        this.setEmail(counselorDto.getEmail());
        this.setPassword(counselorDto.getPassword());
        this.setName(counselorDto.getName());
        this.setGender(counselorDto.getGender());
        this.setBirth(counselorDto.getBirth());
        this.setPoint(counselorDto.getPoint());
        this.setPhoneNumber(counselorDto.getPhoneNumber());
        this.shortGreeting = counselorDto.getShortGreeting();
        this.photo = counselorDto.getPhoto();
        this.greeting = counselorDto.getGreeting();
        this.degree = counselorDto.getDegree();
    }


}
