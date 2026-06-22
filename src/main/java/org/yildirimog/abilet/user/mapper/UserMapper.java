package org.yildirimog.abilet.user.mapper;

import org.springframework.stereotype.Component;
import org.yildirimog.abilet.auth.dto.RegisterRequest;
import org.yildirimog.abilet.user.dto.UserResponseDto;
import org.yildirimog.abilet.user.dto.UserUpdateDto;
import org.yildirimog.abilet.user.entity.User;
import org.yildirimog.abilet.user.enums.Role;

@Component
public class UserMapper {
    public UserResponseDto userToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getRole(),
                user.getCreatedAt()
        );
    }

    public User toEntity(RegisterRequest registerRequest, String hashedPassword) {
        return User.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .email(registerRequest.email())
                .birthDate(registerRequest.birthDate())
                .password(hashedPassword)
                .role(Role.CUSTOMER)
                .build();
    }

    public void updateUserFromDto(User user, UserUpdateDto dto) {
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setBirthDate(dto.birthDate());
    }
}
