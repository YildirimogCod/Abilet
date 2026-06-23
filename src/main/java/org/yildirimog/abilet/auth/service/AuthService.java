package org.yildirimog.abilet.auth.service;

import org.yildirimog.abilet.auth.dto.AuthResponse;
import org.yildirimog.abilet.auth.dto.LoginRequest;
import org.yildirimog.abilet.auth.dto.RegisterRequest;
import org.yildirimog.abilet.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
