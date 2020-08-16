package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.Payment;
import com.go2it.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findPaymentByOrder(Order order);
    List<Payment> findPaymentByCustomer(User cust);
    List<Payment> findPaymentByEmployee(User empl);
    @Query("SELECT " +
            "p " +
            "FROM " +
            "Payment p "+
            "WHERE " +
            "p.customer.userId = :id")
    List<Payment> findPaymentByCustomerId(@Param("id") int id);

    @Query("SELECT " +
            "p " +
            "FROM " +
            "Payment p "+
            "WHERE " +
            "p.employee.userId = :id")
    List<Payment> findPaymentByEmployeeId(@Param("id") int id);

    @Query("SELECT " +
            "p " +
            "FROM " +
            "Payment p "+
            "WHERE " +
            "p.order.orderId = :id")
    List<Payment> findPaymentByOrderId(@Param("id") int id);
}
