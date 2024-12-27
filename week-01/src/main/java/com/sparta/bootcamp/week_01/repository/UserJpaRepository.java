package com.sparta.bootcamp.week_01.repository;

import com.sparta.bootcamp.week_01.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<User, Long> {

  List<User> findByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.role = :role")
  List<User> findByRole(@Param("role") String role);

  List<User> findByAddress(String address);

  Page<User> findByAddress(String address, Pageable pageable);
}
