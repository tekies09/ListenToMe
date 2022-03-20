package com.ssafy.a605.repository;

import com.ssafy.a605.model.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {
    List<Certificate> findAllByCounselor_Email(String userEmail);
}
