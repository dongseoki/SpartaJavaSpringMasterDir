package com.sparta.bootcamp.week_01.service;

import com.sparta.bootcamp.week_01.entity.UserOldV1;
import com.sparta.bootcamp.week_01.repository.old.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserJpaRepository userJpaRepository;

  public int createUser(UserOldV1 userOldV1) {
    log.info("User created: {}", userOldV1);

    return userJpaRepository.save(userOldV1).getId().intValue();
  }
}
