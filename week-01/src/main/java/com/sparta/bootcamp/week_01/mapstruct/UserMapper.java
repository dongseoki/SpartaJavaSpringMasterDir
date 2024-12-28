package com.sparta.bootcamp.week_01.mapstruct;

import com.sparta.bootcamp.week_01.dto.UserRequest;
import com.sparta.bootcamp.week_01.dto.UserRequestV2;
import com.sparta.bootcamp.week_01.dto.UserResponse;
import com.sparta.bootcamp.week_01.dto.UserResponseV2;
import com.sparta.bootcamp.week_01.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserResponseV2 toUserResponse(User user);

  User toEntity(UserRequestV2 userRequestv2);

}
