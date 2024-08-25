package com.codingrecipe.board.entity;

import com.codingrecipe.board.dto.JoinDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_table")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String phonenumber;
    @Column
    private String role;

    public static UserEntity tosaveEntity(JoinDTO joinDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(joinDTO.getUsername());
        userEntity.setPassword(joinDTO.getPassword());
        userEntity.setPhonenumber(joinDTO.getPhonenumber());
        userEntity.setRole("일반인");
        return userEntity;

    }
}

