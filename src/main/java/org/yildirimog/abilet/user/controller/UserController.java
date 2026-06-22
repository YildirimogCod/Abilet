package org.yildirimog.abilet.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yildirimog.abilet.user.dto.UserResponseDto;
import org.yildirimog.abilet.user.dto.UserUpdateDto;
import org.yildirimog.abilet.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.getById(id);

       return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
       return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,@Valid @RequestBody UserUpdateDto userUpdateDto) {
        UserResponseDto userResponseDto = userService.update(id,userUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
