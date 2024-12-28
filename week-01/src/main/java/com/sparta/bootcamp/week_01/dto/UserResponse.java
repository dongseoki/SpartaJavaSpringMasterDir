package com.sparta.bootcamp.week_01.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class UserResponse {

  private String phoneNumber;
  private LocalDate bookingDate;
  private String name;
  private String email;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createDt;

  public static UserResponse of(Map<String, Object> stringStringMap) {
    LocalDate bookingDate1 = LocalDate.parse((String) stringStringMap.get("bookingDate"));
    return UserResponse.builder()
        .phoneNumber((String) stringStringMap.get("phoneNumber"))
        .bookingDate(bookingDate1)
        .name((String) stringStringMap.get("name"))
        .email((String) stringStringMap.get("email"))
        .createDt((LocalDateTime) stringStringMap.get("createDt"))
        .build();
  }
}
