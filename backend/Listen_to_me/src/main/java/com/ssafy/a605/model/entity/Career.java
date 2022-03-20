package com.ssafy.a605.model.entity;


import com.ssafy.a605.model.dto.CareerDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto increment
    private int id;

    @ManyToOne
    @JoinColumn(name = "counselor_email")
    private Counselor counselor; // email 과 counselor_email pk,fk

    private String name;

    @Builder
    Career(Counselor counselor, String name){
        this.counselor = counselor;
        this.name = name;
    }

    public void update(CareerDto careerDto) {
        this.id = careerDto.getId();
        this.counselor = careerDto.getCounselor();
        this.name = careerDto.getName();
    }
}
