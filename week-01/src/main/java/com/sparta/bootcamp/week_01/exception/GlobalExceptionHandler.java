package com.sparta.bootcamp.week_01.exception;

import com.sparta.bootcamp.week_01.web.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden // Swagger 예외 설정
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<?> handleResponseException(ServiceException ex) {
    return ApiResponse.ResponseException(ex.getCode(), ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

    return ApiResponse.ValidException("VALIDATE_ERROR", String.valueOf(errors));
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<?> bindException(BindException ex) {
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

    return ApiResponse.ValidException("VALIDATE_ERROR", String.valueOf(errors));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> serverException(Exception ex) {
    return ApiResponse.ServerException("SERVER_ERROR", ex.getMessage());
  }
}
