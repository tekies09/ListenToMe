package com.ssafy.a605.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "counselor_email")
    private Counselor counselor;

    @ManyToOne
    @JoinColumn(name = "client_email")
    private Client client;

    private int startScore;
    private String content;
}
