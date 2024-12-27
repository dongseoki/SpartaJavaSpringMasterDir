package com.sparta.bootcamp.week_01.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.bootcamp.week_01.entity.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserJpaRepositoryTest {

  @Autowired
  UserJpaRepository userJpaRepository;

  @Test
  void findByEmail() {
  }

  @Test
  void saveTest() {
    User user = User.builder().username("test")
        .email("test@gmail.com")
        .passwordHash("1234")
        .phoneNumber("010-1234-5678")
        .address("서울시 강남구")
        .role("NORMAL_USER")
        .build();
    userJpaRepository.save(user);
  }


  @Test
  void saveSampleDataTest() {
    String[] rolls = {"NORMAL_USER", "ADMIN", "SYSTEM"};
    String[] addresses = {"서울시 강남구", "서울시 강북구", "서울시 동작구", "서울시 서초구", "서울시 송파구"};
    for (int i = 20; i < 100; i++) {
      User user = User.builder().username(String.format("test%d", i + 1))
          .email(String.format("test%d@gmail.com", i + 1))
          .passwordHash("1234")
          .phoneNumber("010-1234-5678")
          .address(addresses[i % 5])
          .role(rolls[i % 3])
          .build();
      userJpaRepository.save(user);
    }

  }

  @Test
  void findByRole() {
    List<User> normal_user = userJpaRepository.findByRole("NORMAL_USER");
    normal_user.forEach(System.out::println);
  }

  @Test
  void findByAddress() {
    List<User> users = userJpaRepository.findByAddress("서울시 강남구");
    users.forEach(System.out::println);
  }

  @Test
  void findByAddressWithPageable() {
    Pageable pageable = Pageable.ofSize(2).withPage(0);
    Page<User> result = userJpaRepository.findByAddress("서울시 강남구", pageable);
    System.out.println("Total elements: " + result.getTotalElements());
  }
}