package com.ssafy.a605.model.dto;

import com.ssafy.a605.model.entity.Certificate;
import com.ssafy.a605.model.entity.Counselor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CareerDto {

    private int id;
    private Counselor counselor;
    private String name;

    public CareerDto(CareerDto careerDto){

        this.id = careerDto.getId();
        this.counselor = careerDto.getCounselor();
        this.name = careerDto.getName();

    }

}
