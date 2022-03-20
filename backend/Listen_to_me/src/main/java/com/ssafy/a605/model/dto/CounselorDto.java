package com.ssafy.a605.model.dto;

import com.ssafy.a605.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounselorDto extends UserDto {
    private String photo;
    private String shortGreeting;
    private String greeting;
    private String degree;

    public CounselorDto(String email, String password, String name, char gender, String birth, int point, String phoneNumber, String photo, String shortGreeting, String greeting, String degree) {
        super(email, password, name, phoneNumber, gender, birth, point);
        this.photo = photo;
        this.shortGreeting = shortGreeting;
        this.greeting = greeting;
        this.degree = degree;
    }
}
