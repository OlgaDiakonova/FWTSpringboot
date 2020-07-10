package com.go2it.springboot.service;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.OrderDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Optional<Order> findById(int id);
    void save(Order order);
    void save(OrderDTO orderDto);
    List<Order> findAll();
    List<Order> findOrderByCustomer(User customer);
    List<Order> findOrderByEmployee(User employee);
//    List<Order> findOrderByOrder_date(LocalDate orderDate);
}
