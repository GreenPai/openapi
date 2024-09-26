package com.codingrecipe.board.point;

import com.codingrecipe.board.login.UserEntity;
import com.codingrecipe.board.musical.MusicalEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "point_table")
@Setter
@Getter
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @Column
    private int point;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid") // 외래 키 컬럼 정의
    private UserEntity userEntity;
}
