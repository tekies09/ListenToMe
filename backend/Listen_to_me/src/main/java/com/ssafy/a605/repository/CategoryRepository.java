package com.ssafy.a605.repository;

import com.ssafy.a605.model.dto.CategoryDto;
import com.ssafy.a605.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
