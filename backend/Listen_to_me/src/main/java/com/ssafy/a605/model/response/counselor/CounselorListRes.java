package com.ssafy.a605.model.response.counselor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CounselorListRes {
    private String email;
    private String name;
    private String photo;
    private double startScore;
    private List<String> category;
}
