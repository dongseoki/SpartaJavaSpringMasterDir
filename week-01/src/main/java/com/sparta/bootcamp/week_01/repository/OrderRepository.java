package com.sparta.bootcamp.week_01.repository;

import com.sparta.bootcamp.week_01.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
