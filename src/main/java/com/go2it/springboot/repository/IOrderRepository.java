package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrderByCustomer(User customer);
    List<Order> findOrderByEmployee(User employee);
//    List<Order> findOrderByOrder_date(LocalDate orderDate);
}
