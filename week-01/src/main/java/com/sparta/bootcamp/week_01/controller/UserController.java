package com.sparta.bootcamp.week_01.controller;

import static com.sparta.bootcamp.week_01.exception.ServiceExceptionCode.NOT_FOUND_USER;

import com.sparta.bootcamp.week_01.dto.UserRequest;
import com.sparta.bootcamp.week_01.dto.UserRequestV2;
import com.sparta.bootcamp.week_01.dto.UserResponse;
import com.sparta.bootcamp.week_01.dto.UserResponseV2;
import com.sparta.bootcamp.week_01.entity.UserOldV1;
import com.sparta.bootcamp.week_01.exception.ServiceException;
import com.sparta.bootcamp.week_01.mapstruct.UserMapper;
import com.sparta.bootcamp.week_01.repository.UserJpaRepository;
import com.sparta.bootcamp.week_01.repository.UserRepository;
import com.sparta.bootcamp.week_01.service.UserService;
import com.sparta.bootcamp.week_01.web.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor

public class UserController {

  private final UserRepository userRepository;
  private final UserJpaRepository userJpaRepository;

  private final UserService userService;


  @Operation(summary = "Create a user")
  @PostMapping
  public ApiResponse createUser(@RequestParam String name, @RequestParam String email) {
    int user = userRepository.createUser(name, email);
    System.out.println("User created!");
    return ApiResponse.Success(Map.of("userNo", user));
  }

  @Operation(summary = "Create a user v2")
  @PostMapping("/v2")
  public ApiResponse createUser2(@Valid @RequestBody UserRequest userRequest) {
    int user = userRepository.createUser(userRequest);
    System.out.println("User created!");
    return ApiResponse.Success(Map.of("userNo", user));
  }

  @Operation(summary = "Create a user v3")
  @PostMapping("/v3")
  public ApiResponse createUser3(@Valid @RequestBody UserRequestV2 userRequestV2) {
    int user = userService.createUser(UserOldV1.generateNormalUser(userRequestV2));
    System.out.println("User created!");
    return ApiResponse.Success(Map.of("userNo", user));
  }

  @Operation(summary = "Get a user")
  @GetMapping("/{idx}")
  public ApiResponse<UserResponse> getUser(@PathVariable Integer idx) {
    System.out.println("User retrieved!");
    Map<String, Object> stringStringMap = userRepository.getUser(idx)
        .orElseThrow(() -> new ServiceException(NOT_FOUND_USER));
    return ApiResponse.Success(UserResponse.of(stringStringMap));
  }

  @Operation(summary = "Get a user v3")
  @GetMapping("/{idx}/v3")
  public ApiResponse<UserResponseV2> getUserV2(@PathVariable Integer idx) {
    System.out.println("User retrieved!");
    UserOldV1 userOldV1 = userJpaRepository.findById(Long.valueOf(idx))
        .orElseThrow(() -> new ServiceException(NOT_FOUND_USER));
    return ApiResponse.Success(UserMapper.INSTANCE.toUserResponse(userOldV1));
  }

  @Operation(summary = "Update a user")
  @PutMapping("/{idx}")
  public void updateUser(@RequestParam String name, @RequestParam String email,
      @PathVariable Integer idx) {
    System.out.println("User updated!");
    userRepository.updateUser(idx, name, email);
  }

  @Operation(summary = "Get a user list")
  @GetMapping("/list")
  public ApiResponse<Page<UserOldV1>> getUserListResponse(Pageable pageable,
      @RequestParam(name = "address") String searchAddress) {
    return ApiResponse.Success(userJpaRepository.findByAddress(searchAddress, pageable));
  }

  @Operation(summary = "Get a user list")
  @GetMapping("/list/v3")
  public ApiResponse<Page<UserResponseV2>> getUserListResponseV3(Pageable pageable,
      @RequestParam(name = "address") String searchAddress) {
    Page<UserOldV1> byAddress = userJpaRepository.findByAddress(searchAddress, pageable);
    Page<UserResponseV2> userResponseV2Page = byAddress.map(UserMapper.INSTANCE::toUserResponse);
    return ApiResponse.Success(userResponseV2Page);
  }
}
