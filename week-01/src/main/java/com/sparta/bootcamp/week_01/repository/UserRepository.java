package com.sparta.bootcamp.week_01.repository;

import java.util.Map;

public interface UserRepository {
  int createUser(String name, String email);
  Map<String, String> getUser(int idx);
  void updateUser(int idx, String name, String email);
}
