package com.ssafy.a605.model.entity;


import com.ssafy.a605.model.dto.CertificateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "counselor_email")
    private Counselor counselor; // email 과 counselor_email pk,fk

    private String name;

    @Builder
    Certificate(Counselor counselor, String name){
        this.counselor = counselor;
        this.name = name;
    }

    public void update(CertificateDto certificateDto){
        this.id = certificateDto.getId();
        this.counselor = certificateDto.getCounselor();
        this.name = certificateDto.getName();

    }
}
