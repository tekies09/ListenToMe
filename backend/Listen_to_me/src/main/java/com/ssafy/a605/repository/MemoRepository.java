package com.ssafy.a605.repository;

import com.ssafy.a605.model.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Integer> {
    boolean existsMemoByUserEmailEqualsAndSchedule_Id(String userEmail, int id);
    Memo getByUserEmailEqualsAndSchedule_Id(String userEmail, int id);
    Memo findById(int id);
    List<Memo> findMemosBySchedule_Id(int id);
}