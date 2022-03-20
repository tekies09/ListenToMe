package com.ssafy.a605.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_email")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "counselor_email")
    private Counselor counselor;

}
