package org.yildirimog.abilet.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto update(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        userMapper.updateEntity(user, userUpdateDto);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
       return userRepository.findAll(pageable)
               .map(userMapper::toDto);
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
