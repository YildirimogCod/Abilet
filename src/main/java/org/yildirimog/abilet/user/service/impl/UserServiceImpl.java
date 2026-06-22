package org.yildirimog.abilet.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yildirimog.abilet.user.dto.UserResponseDto;
import org.yildirimog.abilet.user.dto.UserUpdateDto;
import org.yildirimog.abilet.user.entity.User;
import org.yildirimog.abilet.user.exception.UserNotFoundException;
import org.yildirimog.abilet.user.mapper.UserMapper;
import org.yildirimog.abilet.user.repository.UserRepository;
import org.yildirimog.abilet.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return userMapper.userToUserResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto update(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        userMapper.updateUserFromDto(user, userUpdateDto);
        return userMapper.userToUserResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
       return userRepository.findAll()
               .stream()
               .map((userMapper::userToUserResponseDto))
               .toList();
    }


    @Override
    @Transactional
    public void delete(Long id) {
         User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setDeletedAt(LocalDateTime.now());
        log.info("User soft deleted: id={}", id);
    }
}
