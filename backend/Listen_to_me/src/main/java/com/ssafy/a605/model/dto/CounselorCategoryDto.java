package com.ssafy.a605.model.dto;

import com.ssafy.a605.model.entity.Category;
import com.ssafy.a605.model.entity.Counselor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounselorCategoryDto {
    private int id;
    private Counselor counselor;
    private Category category;
}
