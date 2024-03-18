package com.codingrecipe.board.entity;

import com.codingrecipe.board.dto.MusicalDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "musical_table")
@Setter
@Getter
public class MusicalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long res_no;

    @Column
    private String title;
    @Column
    private String op_st_dt;
    @Column
    private String op_ed_dt;
    @Column
    private String place_nm;
    @Column
    private String op_at;
    @Column
    private String pay_at;


    public static MusicalEntity toSaveEntity(MusicalDTO musicalDTO) {
        MusicalEntity musicalEntity = new MusicalEntity();

        musicalEntity.setOp_at(musicalDTO.getOp_at());
        musicalEntity.setTitle(musicalDTO.getTitle());
        musicalEntity.setPay_at(musicalDTO.getPay_at());
        musicalEntity.setOp_ed_dt(musicalDTO.getOp_ed_dt());
        musicalEntity.setOp_at(musicalDTO.getOp_at());
        musicalEntity.setOp_st_dt(musicalDTO.getOp_st_dt());

        return musicalEntity;

    }
}
