package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrderByCustomer(User customer);
    @Query("SELECT " +
            "o " +
            "FROM " +
            "Order o "+
            "WHERE " +
            "o.customer.userId = :id")
    List<Order> findOrderByCustomerId(@Param("id") int id);
    List<Order> findOrderByEmployee(User employee);
//    List<Order> findOrderByOrder_date(LocalDate orderDate);
}
