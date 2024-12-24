package com.sparta.bootcamp.week_01.controller;

import com.sparta.bootcamp.week_01.repository.UserRepository;
import com.sparta.bootcamp.week_01.web.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @Operation(summary = "Get a user")
  @GetMapping("/{idx}")
  public ApiResponse<Map<String,String>> getUser(@PathVariable Integer idx) {
    System.out.println("User retrieved!");
    return ApiResponse.Success(userRepository.getUser(idx));
  }

  @Operation(summary = "Update a user")
  @PutMapping("/{idx}")
  public void updateUser(@RequestParam String name, @RequestParam String email, @PathVariable Integer idx) {
    System.out.println("User updated!");
    userRepository.updateUser(idx, name, email);
  }
}
