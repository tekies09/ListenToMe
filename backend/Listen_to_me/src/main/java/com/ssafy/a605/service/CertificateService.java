package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.CertificateDto;
import com.ssafy.a605.model.entity.Certificate;

import java.util.List;

public interface CertificateService {
    public List<CertificateDto> getCounselorCertificate(String userEmail) throws Exception;
    public boolean setCertificate(String userEmail, String name) throws Exception;
}
