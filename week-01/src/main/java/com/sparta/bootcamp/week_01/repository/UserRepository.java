package com.sparta.bootcamp.week_01.repository;

import com.sparta.bootcamp.week_01.dto.UserRequest;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {
  int createUser(String name, String email);
  Optional<Map<String, Object>> getUser(int idx);
  void updateUser(int idx, String name, String email);

  int createUser(UserRequest userRequest);
}
