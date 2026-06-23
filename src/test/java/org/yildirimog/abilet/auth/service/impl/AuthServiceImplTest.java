package org.yildirimog.abilet.auth.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yildirimog.abilet.auth.dto.RegisterRequest;
import org.yildirimog.abilet.auth.dto.RegisterResponse;
import org.yildirimog.abilet.user.entity.User;
import org.yildirimog.abilet.user.enums.Role;
import org.yildirimog.abilet.user.exception.UserAlreadyExistException;
import org.yildirimog.abilet.user.mapper.UserMapper;
import org.yildirimog.abilet.user.repository.UserRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    private RegisterRequest sampleRequest() {
        return new RegisterRequest("Ada", "Lovelace", "ada@example.com", "secret12", LocalDate.of(1990, 1, 1));
    }

    @Test
    void register_whenEmailExists_throwsAndDoesNotSave() {
        RegisterRequest request = sampleRequest();
        when(userRepository.existsByEmail("ada@example.com")).thenReturn(true);

        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(UserAlreadyExistException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    void register_hashesPasswordAndReturnsResponse() {
        RegisterRequest request = sampleRequest();
        User mapped = User.builder().email("ada@example.com").password("hashed").role(Role.CUSTOMER).build();
        User saved = User.builder().id(1L).email("ada@example.com").password("hashed").role(Role.CUSTOMER).build();

        when(userRepository.existsByEmail("ada@example.com")).thenReturn(false);
        when(passwordEncoder.encode("secret12")).thenReturn("hashed");
        when(userMapper.toEntity(eq(request), eq("hashed"))).thenReturn(mapped);
        when(userRepository.save(mapped)).thenReturn(saved);

        RegisterResponse response = authService.register(request);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.email()).isEqualTo("ada@example.com");
        assertThat(response.role()).isEqualTo(Role.CUSTOMER);
        verify(passwordEncoder).encode("secret12");
    }
}
