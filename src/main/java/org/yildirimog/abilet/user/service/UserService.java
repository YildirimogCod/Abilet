package org.yildirimog.abilet.user.service;

import org.yildirimog.abilet.user.dto.UserResponseDto;
import org.yildirimog.abilet.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserResponseDto getById(Long id);
    UserResponseDto update(Long id, UserUpdateDto userUpdateDto);
    List<UserResponseDto> getAllUsers();
    void delete(Long id);
}
