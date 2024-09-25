package com.codingrecipe.board.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 서비스
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

    // 회원가입 서비스
    public void save(JoinDTO joinDTO) {

        // 비밀번호 암호화
        String pwd = bCryptPasswordEncoder.encode(joinDTO.getPassword());
        joinDTO.setPassword(pwd);

        UserEntity userEntity = UserEntity.tosaveEntity(joinDTO);
        userRepository.save(userEntity);

    }

    // 로그인 테스트
    public JoinDTO test(JoinDTO joinDTO){
        String username = joinDTO.getUsername();
        UserEntity userData = userRepository.findByUsername(username);

        JoinDTO joinDTO1 = JoinDTO.tosaveDto(userData);
        return joinDTO1;
    }

    /**
     *
     * 회원가입 아이디 중복체크
     */
    public boolean checkId(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null){
            return false;
        }
        return true;
    }

    public boolean checkIdAndPassword(String username, String password) {
        String pwd = bCryptPasswordEncoder.encode(password);
        UserEntity userEntity = userRepository.findByUsernameAndPassword(username,pwd);
        if (userEntity != null){
            return false;
        }
        return true;
    }
}
