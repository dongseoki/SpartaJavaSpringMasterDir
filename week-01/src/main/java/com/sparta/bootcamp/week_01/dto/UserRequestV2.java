package com.sparta.bootcamp.week_01.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
// only private constructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequestV2 {

  @Schema(example = "John Doe")
  private String username;

  @Schema(example = "test@gmail.com")
  private String email;

  @Schema(example = "hashed_password")
  private String passwordHash;

  @Schema(example = "01012345678")
  private String phoneNumber;

  @Schema(example = "123 Main St, Anytown, USA")
  private String address;

}
