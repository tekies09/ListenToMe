package com.ssafy.a605.repository;

import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.Counselor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounselorRepository extends JpaRepository<Counselor,Long> {
    Optional<Counselor> findByEmail(String email);
    void deleteByEmail(String email);
}
