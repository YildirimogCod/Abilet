package org.yildirimog.abilet.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yildirimog.abilet.user.dto.UserResponseDto;
import org.yildirimog.abilet.user.dto.UserUpdateDto;

public interface UserService {
    UserResponseDto getById(Long id);
    UserResponseDto update(Long id, UserUpdateDto userUpdateDto);
    Page<UserResponseDto> getAllUsers(Pageable pageable);
    void delete(Long id);
}
