package com.sparta.bootcamp.week_01.repository;

import com.sparta.bootcamp.week_01.entity.UserOldV1;
import com.sparta.bootcamp.week_01.repository.old.UserJpaRepository;
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
class UserOldV1JpaRepositoryTest {

  @Autowired
  UserJpaRepository userJpaRepository;

  @Test
  void findByEmail() {
  }

  @Test
  void saveTest() {
    UserOldV1 userOldV1 = UserOldV1.builder().username("test")
        .email("test@gmail.com")
        .passwordHash("1234")
        .phoneNumber("010-1234-5678")
        .address("서울시 강남구")
        .role("NORMAL_USER")
        .build();
    userJpaRepository.save(userOldV1);
  }


  @Test
  void saveSampleDataTest() {
    String[] rolls = {"NORMAL_USER", "ADMIN", "SYSTEM"};
    String[] addresses = {"서울시 강남구", "서울시 강북구", "서울시 동작구", "서울시 서초구", "서울시 송파구"};
    for (int i = 20; i < 100; i++) {
      UserOldV1 userOldV1 = UserOldV1.builder().username(String.format("test%d", i + 1))
          .email(String.format("test%d@gmail.com", i + 1))
          .passwordHash("1234")
          .phoneNumber("010-1234-5678")
          .address(addresses[i % 5])
          .role(rolls[i % 3])
          .build();
      userJpaRepository.save(userOldV1);
    }

  }

  @Test
  void findByRole() {
    List<UserOldV1> normal_userOldV1 = userJpaRepository.findByRole("NORMAL_USER");
    normal_userOldV1.forEach(System.out::println);
  }

  @Test
  void findByAddress() {
    List<UserOldV1> userOldV1s = userJpaRepository.findByAddress("서울시 강남구");
    userOldV1s.forEach(System.out::println);
  }

  @Test
  void findByAddressWithPageable() {
    Pageable pageable = Pageable.ofSize(2).withPage(0);
    Page<UserOldV1> result = userJpaRepository.findByAddress("서울시 강남구", pageable);
    System.out.println("Total elements: " + result.getTotalElements());
  }
}