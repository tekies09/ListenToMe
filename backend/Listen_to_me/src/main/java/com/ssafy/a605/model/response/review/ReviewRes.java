package com.ssafy.a605.model.response.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewRes {
    private int id;
    private String nickname;
    private int startScore;
    private String content;
}
