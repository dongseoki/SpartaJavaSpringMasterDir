package com.sparta.bootcamp.week_01.jpa;

import com.sparta.bootcamp.week_01.entity.Order;
import com.sparta.bootcamp.week_01.entity.User;
import com.sparta.bootcamp.week_01.repository.OrderRepository;
import com.sparta.bootcamp.week_01.repository.UserRepository;
import java.math.BigDecimal;
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
}
