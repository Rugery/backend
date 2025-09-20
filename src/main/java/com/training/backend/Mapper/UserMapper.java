package com.training.backend.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.training.backend.Dto.UserRegisterDto;
import com.training.backend.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "progress", ignore = true)
  @Mapping(target = "insignias", ignore = true)
  User toEntity(UserRegisterDto dto);

  UserRegisterDto toDto(User user);

}
