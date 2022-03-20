package com.ssafy.a605.repository;

import com.ssafy.a605.model.dto.CategoryDto;
import com.ssafy.a605.model.dto.CounselorCategoryDto;
import com.ssafy.a605.model.entity.Category;
import com.ssafy.a605.model.entity.CounselorCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounselorCategoryRepository extends JpaRepository<CounselorCategory, Integer> {
    boolean existsByCounselor_EmailAndCategory_IdEquals(String userEmail, int categoryId);
    List<CounselorCategory> findAllByCounselor_Email(String userEmail);
}
