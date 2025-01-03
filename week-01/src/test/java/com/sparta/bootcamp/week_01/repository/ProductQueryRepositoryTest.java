package com.sparta.bootcamp.week_01.repository;


import com.sparta.bootcamp.week_01.config.TestConfig;
import com.sparta.bootcamp.week_01.entity.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

//@DataJpaTest
//@Import(TestConfig.class) //추가
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Transactional
class ProductQueryRepositoryTest {

  @Autowired
  private ProductQueryRepository productQueryRepository;

  @Test
  void search1() {
    System.out.println("search1 start");
    List<Product> test = productQueryRepository.search("test", null, null);
    test.forEach(System.out::println);
    System.out.println("search1 end");
  }

  @Test
  void search2() {
    System.out.println("search2 start");
    List<Product> test = productQueryRepository.search("test", BigDecimal.valueOf(1000L),
        BigDecimal.valueOf(10000L));
    test.forEach(System.out::println);
    System.out.println("search2 end");
  }

  @Test
  void searchcomplex1() {
    System.out.println("searchcomplex1 start");
    List<Product> test = productQueryRepository.searchComplex("test", null, null);
    test.forEach(System.out::println);
    System.out.println("searchcomplex1 end");
  }

  @Test
  void searchcomplex2() {
    System.out.println("searchcomplex2 start");
    List<Product> test = productQueryRepository.searchComplex("test", BigDecimal.valueOf(1000L),
        BigDecimal.valueOf(10000L));
    test.forEach(System.out::println);
    System.out.println("searchcomplex2 end");
  }
}