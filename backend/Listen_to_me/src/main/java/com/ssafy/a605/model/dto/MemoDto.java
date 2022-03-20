package com.ssafy.a605.model.dto;

import com.ssafy.a605.model.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoDto {
    private int id;
    private Schedule schedule;
    private String userEmail;
    private String title;
    private String content;
}
