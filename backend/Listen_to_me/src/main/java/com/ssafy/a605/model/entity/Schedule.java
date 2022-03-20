package com.ssafy.a605.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.a605.model.dto.ScheduleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_email")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "counselor_email")
    private Counselor counselor;

    private LocalDateTime dateTime;
    private String topic;
    private int point;
    private int state;
    private boolean isSticker;

    public void setTime(ScheduleDto scheduleDto){
        this.setDateTime(scheduleDto.getDateTime());
        this.setPoint(1000);
        this.setState(0);
    }
}
