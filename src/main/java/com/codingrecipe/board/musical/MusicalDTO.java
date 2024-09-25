package com.codingrecipe.board.musical;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MusicalDTO {

    private Long res_no;
    private String title;
    private String op_st_dt;
    private String op_ed_dt;
    private String op_at;
    private String place_nm;
    private String pay_at;

    public static MusicalDTO convertToDTO(MusicalEntity existingMusical) {
        MusicalDTO musicalDTO = new MusicalDTO();
        musicalDTO.setRes_no(existingMusical.getResno());
        musicalDTO.setTitle(existingMusical.getTitle());
        musicalDTO.setPay_at(existingMusical.getPay_at());
        musicalDTO.setOp_at(existingMusical.getOp_at());
        musicalDTO.setOp_st_dt(existingMusical.getOp_st_dt());
        musicalDTO.setOp_ed_dt(existingMusical.getOp_ed_dt());
        musicalDTO.setPlace_nm(existingMusical.getPlace_nm());
        return musicalDTO;
    }


    public static String convertTitleToDTO(MusicalEntity musicalEntity) {
        MusicalDTO musicalDTO = new MusicalDTO();
        musicalDTO.setPlace_nm(musicalDTO.getPlace_nm());
        return musicalDTO.getPlace_nm();
    }


    public static String convertToDTOTitle(MusicalEntity musicalEntity) {
        MusicalDTO musicalDTO = new MusicalDTO();
        musicalDTO.setPlace_nm(musicalEntity.getPlace_nm());
        return musicalDTO.getPlace_nm();
    }
}
