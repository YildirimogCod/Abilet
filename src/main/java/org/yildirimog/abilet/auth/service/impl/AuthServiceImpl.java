package org.yildirimog.abilet.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yildirimog.abilet.auth.dto.AuthResponse;
import org.yildirimog.abilet.auth.dto.LoginRequest;
import org.yildirimog.abilet.auth.dto.RegisterRequest;
import org.yildirimog.abilet.user.exception.UserAlreadyExistException;
import org.yildirimog.abilet.auth.service.AuthService;
import org.yildirimog.abilet.user.entity.User;
import org.yildirimog.abilet.user.enums.Role;
import org.yildirimog.abilet.user.mapper.UserMapper;
import org.yildirimog.abilet.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new UserAlreadyExistException(registerRequest.email());
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.password());
        User savedUser = userRepository.save(userMapper.toEntity(registerRequest, encodedPassword));

        return new AuthResponse(null, null, savedUser.getEmail(), Role.CUSTOMER);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // JWT entegrasyonu sonrası implement edilecek
        throw new UnsupportedOperationException("JWT henüz eklenmedi");
    }
}
