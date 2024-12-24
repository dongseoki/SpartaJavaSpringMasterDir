package com.sparta.bootcamp.week_01.web;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T> {

  private final Boolean reuslt;
  private final Error error;
  private final T message;

  public ApiResponse(Boolean reuslt, String errorCode, String errorMessage, T message) {
    this.reuslt = reuslt;
    this.error = Error.builder()
        .errorCode(errorCode)
        .errorMessage(errorMessage)
        .build();
    this.message = message;
  }

  public static <T> ApiResponse<T> Success(T message) {
    return new ApiResponse<>(true, null, null, message);
  }

  public static <T> ResponseEntity<ApiResponse<T>> ResponseException(String code,
      String errorMessage) {
    return ResponseEntity.ok(new ApiResponse<>(false, code, errorMessage, null));
  }

  public static <T> ResponseEntity<ApiResponse<T>> ValidException(String code,
      String errorMessage) {
    return ResponseEntity.ok(new ApiResponse<>(false, code, errorMessage, null));
  }

  public static <T> ResponseEntity<ApiResponse<T>> ServerException(String code,
      String errorMessage) {
    return ResponseEntity.status(500)
        .body(new ApiResponse<>(false, code, errorMessage, null));
  }

  @Getter
  public static class Error {

    private final String errorCode;
    private final String errorMessage;

    @Builder
    public Error(String errorCode, String errorMessage) {
      this.errorCode = errorCode;
      this.errorMessage = errorMessage;
    }
  }
}