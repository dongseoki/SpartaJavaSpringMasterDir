package com.sparta.bootcamp.week_01.mapstruct;

import com.sparta.bootcamp.week_01.dto.UserRequestV2;
import com.sparta.bootcamp.week_01.dto.UserResponseV2;
import com.sparta.bootcamp.week_01.entity.UserOldV1;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserResponseV2 toUserResponse(UserOldV1 userOldV1);

  UserOldV1 toEntity(UserRequestV2 userRequestv2);

}
