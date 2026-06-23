package org.yildirimog.abilet.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yildirimog.abilet.auth.dto.RegisterRequest;
import org.yildirimog.abilet.auth.dto.RegisterResponse;
import org.yildirimog.abilet.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
      RegisterResponse registerResponse = authService.register(registerRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }


}
