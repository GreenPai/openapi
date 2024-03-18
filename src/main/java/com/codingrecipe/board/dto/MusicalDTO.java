package com.codingrecipe.board.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MusicalDTO {

    private String res_no;
    private String title;
    private String op_st_dt;
    private String op_ed_dt;
    private String op_at;
    private String place_nm;
    private String pay_at;

}
