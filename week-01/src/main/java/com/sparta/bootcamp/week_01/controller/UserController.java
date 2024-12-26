package com.sparta.bootcamp.week_01.controller;

import static com.sparta.bootcamp.week_01.exception.ServiceExceptionCode.NOT_FOUND_USER;

import com.sparta.bootcamp.week_01.dto.UserRequest;
import com.sparta.bootcamp.week_01.dto.UserResponse;
import com.sparta.bootcamp.week_01.exception.ServiceException;
import com.sparta.bootcamp.week_01.repository.UserRepository;
import com.sparta.bootcamp.week_01.web.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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


  @Operation(summary = "Create a user")
  @PostMapping
  public ApiResponse createUser(@RequestParam String name, @RequestParam String email) {
    int user = userRepository.createUser(name, email);
    System.out.println("User created!");
    return ApiResponse.Success(Map.of("userNo", user));
  }

  @Operation(summary = "Create a user")
  @PostMapping("/v2")
  public ApiResponse createUser2(@Valid @RequestBody UserRequest userRequest) {
    int user = userRepository.createUser(userRequest);
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

  @Operation(summary = "Update a user")
  @PutMapping("/{idx}")
  public void updateUser(@RequestParam String name, @RequestParam String email, @PathVariable Integer idx) {
    System.out.println("User updated!");
    userRepository.updateUser(idx, name, email);
  }
}
