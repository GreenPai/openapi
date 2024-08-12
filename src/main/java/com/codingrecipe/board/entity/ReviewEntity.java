package com.codingrecipe.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column
    private String title;

    @Column
    private String cont;

    @Column
    private Double star;
}
