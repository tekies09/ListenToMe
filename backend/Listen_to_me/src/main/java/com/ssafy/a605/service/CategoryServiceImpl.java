package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.CategoryDto;
import com.ssafy.a605.model.dto.CounselorCategoryDto;
import com.ssafy.a605.model.entity.Category;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.model.entity.CounselorCategory;
import com.ssafy.a605.repository.CategoryRepository;
import com.ssafy.a605.repository.CounselorCategoryRepository;
import com.ssafy.a605.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    final private CounselorRepository counselorRepository;
    final private CategoryRepository categoryRepository;
    final private CounselorCategoryRepository counselorCategoryRepository;

    @Override
    public List<Category> getAllCategory() throws Exception {
        return categoryRepository.findAll();
    }

    @Override
    public List<CounselorCategory> getCounselorCategory(String userEmail) throws Exception {
        return counselorCategoryRepository.findAllByCounselor_Email(userEmail);
    }

    @Override
    public boolean setCounselorCategory(int categoryId, String userEmail) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()->  new NullPointerException("카테고리가 존재 하지 않습니다")
        );
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        CounselorCategory counselorCategory = new CounselorCategory();
        counselorCategory.setCategory(category);
        counselorCategory.setCounselor(counselor);
        CounselorCategory ret = counselorCategoryRepository.save(counselorCategory);
        return ret.equals(counselorCategory);
    }

    @Override
    public boolean checkCounselorCategory(int categoryId, String userEmail) throws Exception {
        return counselorCategoryRepository.existsByCounselor_EmailAndCategory_IdEquals(userEmail, categoryId);
    }
}
