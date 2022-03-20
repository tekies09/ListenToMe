package com.ssafy.a605.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CounselorCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "counselor_email")
    @JsonIgnore
    private Counselor counselor;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
