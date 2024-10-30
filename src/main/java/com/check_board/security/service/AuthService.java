package com.check_board.security.service;

import com.check_board.security.dto.LoginDto;
import com.check_board.security.dto.TokenDto;
import com.check_board.security.dto.UserDto;
import com.check_board.security.entity.RolEntity;
import com.check_board.security.entity.UserEntity;
import com.check_board.security.jwt.JwtService;
import com.check_board.security.repository.RolRepository;
import com.check_board.security.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenDto login(LoginDto request) {
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (user != null) {
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return new TokenDto(jwtService.getToken(user));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public TokenDto register(UserDto request) {
        UserEntity user = new UserEntity();
        RolEntity rol = rolRepository.findByName("QA").orElse(null);

        user.setId(userRepository.findMaxId() + 1);
        user.setUsername(request.getUsername());
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);
        user.setEmail(request.getEmail());
        user.setRol(rol);
        userRepository.save(user);

        return new TokenDto(jwtService.getToken(user));
    }
}
