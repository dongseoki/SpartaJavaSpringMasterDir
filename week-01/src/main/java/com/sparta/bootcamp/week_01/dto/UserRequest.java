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
public class UserRequest {
  @Schema(example = "01012345678")
  @Pattern(regexp = "^\\d{11}$", message = "Phone number must be 10 digits")
  private String phoneNumber;

  @Schema(example = "2025-08-01")
  @Future(message = "Date must be in the future")
  private LocalDate bookingDate;

  @Schema(example = "John Doe")
  @NotBlank
  private String name;

  @Schema(example = "test@gmail.com")
  @NotBlank
  @Email
  private String email;

}
