package com.sparta.bootcamp.week_01.jpa;

import com.sparta.bootcamp.week_01.entity.Order;
import com.sparta.bootcamp.week_01.entity.User;
import com.sparta.bootcamp.week_01.repository.OrderRepository;
import com.sparta.bootcamp.week_01.repository.UserRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class jpaTest {

  @Autowired
  private EntityManager entityManager;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private OrderRepository orderRepository;


  @Test
  void test() {
//    System.out.println("userRepository = " + userRepository);
//    System.out.println("orderRepository = " + orderRepository);
    // random string legnth 5.
    String random = UUID.randomUUID().toString().substring(0, 5);
    User user = User.builder().name("test").email("%s@gmail.com".formatted(random)).password("1234")
        .build();
    userRepository.save(user);
    Order order
        = Order.builder().totalPrice(BigDecimal.valueOf(1000)).build();
    user.addOrder(order);
    orderRepository.save(order);
  }

  @Test
  void nplusOneTestDataInsert() {

    for (int i = 0; i < 100; i++) {
      String random = UUID.randomUUID().toString().substring(0, 20);
      User user = User.builder().name(random).email("%s@gmail.com".formatted(random))
          .password(random)
          .build();
      userRepository.save(user);
      Order order
          = Order.builder().totalPrice(BigDecimal.valueOf(1000)).build();
      user.addOrder(order);
      orderRepository.save(order);
    }
  }

  @Test
  void nplusOneOccurTest() {
    List<User> users = userRepository.findAll();

    for (User user : users) {
      System.out.println("order size : " + user.getOrders().size());
    }
  }

  @Test
  void nplusOnePreventTest() {
    List<User> users = userRepository.findAllWithOrder();

    for (User user : users) {
      System.out.println("order size : " + user.getOrders().size());
    }
  }
}
