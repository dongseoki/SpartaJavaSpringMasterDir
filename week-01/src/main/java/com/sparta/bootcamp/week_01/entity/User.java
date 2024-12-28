package com.sparta.bootcamp.week_01.entity;

import com.sparta.bootcamp.week_01.dto.UserRequestV2;
import com.sparta.bootcamp.week_01.mapstruct.UserMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, length = 50)
  String username;

  @Column(nullable = false, unique = true, length = 255)
  String email;

  @Column(nullable = false, length = 255)
  String passwordHash;

  @Column(length = 15)
  String phoneNumber;

  @Column(columnDefinition = "TEXT")
  String address;

  @Column(length = 20, nullable = false)
  String role;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public User(
      String username,
      String email,
      String passwordHash,
      String phoneNumber,
      String address,
      String role) {
    this.username = username;
    this.email = email;
    this.passwordHash = passwordHash;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.role = role;
  }

  public static User generateNormalUser(UserRequestV2 userRequestV2) {
    User user = UserMapper.INSTANCE.toEntity(userRequestV2);
    User normalUser = User.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .passwordHash(user.getPasswordHash())
        .phoneNumber(user.getPhoneNumber())
        .address(user.getAddress())
        .role("NORMAL_USER")
        .build();
    return normalUser;
  }
}
