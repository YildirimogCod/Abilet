package org.yildirimog.abilet.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.yildirimog.abilet.auth.dto.RegisterRequest;
import org.yildirimog.abilet.user.dto.UserResponseDto;
import org.yildirimog.abilet.user.dto.UserUpdateDto;
import org.yildirimog.abilet.user.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDto toDto(User user);

    @Mapping(target = "password", source = "hashedPassword")
    @Mapping(target = "role", constant = "CUSTOMER")
    User toEntity(RegisterRequest registerRequest, String hashedPassword);

    void updateEntity(@MappingTarget User user, UserUpdateDto dto);
}
