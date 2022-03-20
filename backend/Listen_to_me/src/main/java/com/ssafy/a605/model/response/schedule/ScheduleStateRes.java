package com.ssafy.a605.model.response.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleStateRes {
    private int id;
    private LocalDateTime dateTime;
    private int state;
}
