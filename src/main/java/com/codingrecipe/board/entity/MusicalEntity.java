package com.codingrecipe.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "musical_table")
@Setter
@Getter
public class MusicalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int res_no;

    @Column
    private String title;
    @Column
    private String op_st_dt;
    @Column
    private String op_ed_dt;
    @Column
    private String place_nm;
    @Column
    private char pay_ad;

    public MusicalEntity(int res_no, String title, String op_st_dt, String op_ed_dt, String place_nm, char pay_ad) {
        this.res_no = res_no;
        this.title = title;
        this.op_st_dt = op_st_dt;
        this.op_ed_dt = op_ed_dt;
        this.place_nm = place_nm;
        this.pay_ad = pay_ad;
    }
}
