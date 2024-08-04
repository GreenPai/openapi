package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String pwd);
}
