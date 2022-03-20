package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.CertificateDto;
import com.ssafy.a605.model.entity.Certificate;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.repository.CertificateRepository;
import com.ssafy.a605.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CertificateServiceImpl implements CertificateService {
    final private CounselorRepository counselorRepository;
    final private CertificateRepository certificateRepository;

    @Override
    public List<CertificateDto> getCounselorCertificate(String userEmail) throws Exception {
        List<Certificate> certificate = certificateRepository.findAllByCounselor_Email(userEmail);
        List<CertificateDto> returnCertificate = new ArrayList<>();
        for(Certificate c : certificate){
            returnCertificate.add(new CertificateDto(c.getId(), c.getName()));
        }
        return returnCertificate;
    }

    @Override
    public boolean setCertificate(String userEmail, String name) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        Certificate certificate = new Certificate();
        certificate.setCounselor(counselor);
        certificate.setName(name);
        Certificate ret = certificateRepository.save(certificate);
        return ret.equals(certificate);
    }
}
