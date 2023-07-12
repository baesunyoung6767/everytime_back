package com.example.everytime.service;

import com.example.everytime.DTO.UserSessionDto;
import com.example.everytime.entity.User;
import com.example.everytime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(user_id).orElseThrow(()->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + user_id));

        session.setAttribute("user", new UserSessionDto(user));

        return new CustomUserDetails(user);
    }
}
