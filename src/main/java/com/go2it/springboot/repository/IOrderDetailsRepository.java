package com.go2it.springboot.repository;

import com.go2it.springboot.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

    @Query("SELECT " +
            "od " +
            "FROM " +
            "OrderDetails od " +
            "WHERE " +
            "od.order.orderId = :id")
    List<OrderDetails> findOrderDetailsByOrderId(@Param("id") int id);

}
