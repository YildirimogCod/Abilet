package org.yildirimog.abilet.auth.service;

import org.yildirimog.abilet.auth.dto.AuthResponse;
import org.yildirimog.abilet.auth.dto.LoginRequest;
import org.yildirimog.abilet.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
