package com.ssafy.a605.model.dto;

import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.Counselor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDto {
    private int id;
    private Client client;
    private Counselor counselor;

    private int startScore;
    private String content;
}
