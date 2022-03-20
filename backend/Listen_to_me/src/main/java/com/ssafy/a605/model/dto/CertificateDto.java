package com.ssafy.a605.model.dto;


import com.ssafy.a605.model.entity.Certificate;
import com.ssafy.a605.model.entity.Counselor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateDto {
    private int id;
    private Counselor counselor;
    private String name;

    public CertificateDto(Certificate certificate){
        this.id = certificate.getId();
        this.counselor = certificate.getCounselor();
        this.name = certificate.getName();
    }

    public CertificateDto(int id, String name){
        this.id = id;
        this.name = name;
    }
}
