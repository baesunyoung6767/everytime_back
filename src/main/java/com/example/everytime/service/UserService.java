package com.example.everytime.service;

import com.example.everytime.DTO.UserDto;
import com.example.everytime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public String join(UserDto dto) {
        dto.setUser_pwd(encoder.encode(dto.getUser_pwd()));
        return userRepository.save(dto.toEntity()).getUser_id();
    }

}
