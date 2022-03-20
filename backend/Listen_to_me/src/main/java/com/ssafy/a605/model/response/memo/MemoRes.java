package com.ssafy.a605.model.response.memo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemoRes {
    private int id;
    private LocalDateTime dateTime;
    private String title;
    private String content;
}