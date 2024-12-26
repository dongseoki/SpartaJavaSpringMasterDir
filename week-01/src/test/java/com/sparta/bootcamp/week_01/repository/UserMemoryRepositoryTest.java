package com.sparta.bootcamp.week_01.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class UserMemoryRepositoryTest {

  @Test
  void StringToLocalDate(){
    // given
    String test1 = "2025-08-01";
    // then
    // convert string to localDate
    LocalDate localDate = LocalDate.parse(test1);
    // check if the conversion is correct
    assertEquals(2025, localDate.getYear());
  }

}