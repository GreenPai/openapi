package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.JoinDTO;
import com.codingrecipe.board.entity.UserEntity;
import com.codingrecipe.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDTO joinDTO){

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if(isExist){
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        // 비밀번호는 바로 넣으며 안되고 무조건 암호화 해야된다.
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);


    }

    public void save(JoinDTO joinDTO) {

        // 비밀번호 암호화
        String pwd = bCryptPasswordEncoder.encode(joinDTO.getPassword());
        joinDTO.setPassword(pwd);

        UserEntity userEntity = UserEntity.tosaveEntity(joinDTO);
        userRepository.save(userEntity);

    }

    public UserEntity login(String name, String pwd) {
        UserEntity userEntity = userRepository.findByUsername(name);
        return null;
    }
}
