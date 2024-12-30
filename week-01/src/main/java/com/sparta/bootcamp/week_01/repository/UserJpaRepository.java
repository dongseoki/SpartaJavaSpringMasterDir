package com.sparta.bootcamp.week_01.repository;

import com.sparta.bootcamp.week_01.entity.UserOldV1;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<UserOldV1, Long> {

  List<UserOldV1> findByEmail(String email);

  @Query("SELECT u FROM UserOldV1 u WHERE u.role = :role")
  List<UserOldV1> findByRole(@Param("role") String role);

  List<UserOldV1> findByAddress(String address);

  Page<UserOldV1> findByAddress(String address, Pageable pageable);
}
