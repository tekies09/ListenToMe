package com.ssafy.a605.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.Counselor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleDto {
    private int id;
    private Client client;
    private Counselor counselor;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private String topic;
    private int point;
    private int state;
    private boolean isSticker;
}
