package com.example.everytime.service;

import com.example.everytime.constant.AppException;
import com.example.everytime.constant.ErrorCode;
import com.example.everytime.constant.JwtTokenUtil;
import com.example.everytime.entity.User;
import com.example.everytime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.token.secret}")
    private String secretKey;

    public User saveUser(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findByUserId(user.getUserId());
        if(findUser != null) {
            throw new AppException(ErrorCode.DUPLICATED_USER_ID); // 이미 가입된 회원 아이디
        }
    }

    public String login(String userId, String password) {
        User user = userRepository.findByUserId(userId);

        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND); // 아이디를 찾을 수 없다
        }

        if(!passwordEncoder.matches(password, user.getUserPwd())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD); // 비밀번호가 틀렸다
        }
        return JwtTokenUtil.createToken(userId, secretKey);
    }

    public User getUserByUserAccount(String userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND); // 아이디를 찾을 수 없다
        }
        return user;
    }
}
