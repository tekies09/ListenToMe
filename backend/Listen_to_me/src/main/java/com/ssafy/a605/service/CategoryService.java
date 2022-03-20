package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.CategoryDto;
import com.ssafy.a605.model.dto.CounselorCategoryDto;
import com.ssafy.a605.model.entity.Category;
import com.ssafy.a605.model.entity.CounselorCategory;

import java.util.List;

public interface CategoryService {
    public List<Category>  getAllCategory() throws Exception;
    public List<CounselorCategory> getCounselorCategory(String userEmail) throws Exception;
    public boolean setCounselorCategory(int categoryId, String userEmail) throws Exception;
    public boolean checkCounselorCategory(int categoryId, String userEmail) throws Exception;
}
