package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.CustomUserDetails;
import com.codingrecipe.board.entity.UserEntity;
import com.codingrecipe.board.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username);

        if(userData != null){
           //  System.out.println("널이 아님");
            return new CustomUserDetails(userData);
        }
       // System.out.println("널임");
        return null;
    }
}
