package com.ssafy.a605.model.request.counselor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class CounselorInfoReq {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private char gender;
    private String birth;
    private MultipartFile photo;
    private String shortGreeting;
    private String greeting;
    private String degree;
}
