package com.codingrecipe.board.login;

import lombok.*;

@Setter
@Getter

public class JoinDTO {

    private String username;
    private String password;
    private String phonenumber;


    public static JoinDTO tosaveDto(UserEntity userEntity) {
        JoinDTO joinDTO = new JoinDTO();
        joinDTO.setUsername(userEntity.getUsername());
        joinDTO.setPassword(userEntity.getPassword());
        return joinDTO;
    }
}
