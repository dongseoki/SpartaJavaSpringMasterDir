package com.sparta.bootcamp.week_01.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPAQueryFactoryTest {

  @Autowired
  private JPAQueryFactory jpaQueryFactory;

  @Test
  void testJPAQueryFactoryBean() {
    assertNotNull(jpaQueryFactory, "JPAQueryFactory should be available in the Spring context");
  }
}