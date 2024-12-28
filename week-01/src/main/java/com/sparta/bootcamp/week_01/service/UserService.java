package com.sparta.bootcamp.week_01.service;

import com.sparta.bootcamp.week_01.entity.User;
import com.sparta.bootcamp.week_01.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserJpaRepository userJpaRepository;

  public int createUser(User user) {
    log.info("User created: {}", user);

    return userJpaRepository.save(user).getId().intValue();
  }
}
