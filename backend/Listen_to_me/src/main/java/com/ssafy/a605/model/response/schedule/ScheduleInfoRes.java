package com.ssafy.a605.model.response.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleInfoRes {
    private int id;
    private String client_email;
    private String client_name;
    private String counselor_email;
    private String counselor_name;
    private LocalDateTime dateTime;
    private int point;
    private int state;
    private boolean isSticker;
}
