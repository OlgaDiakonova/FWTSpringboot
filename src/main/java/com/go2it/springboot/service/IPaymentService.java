package com.go2it.springboot.service;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.Payment;
import com.go2it.springboot.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    Optional<Payment> findById(int id);
    void save(Payment pmnt);
    List<Payment> findAll();
    List<Payment> findPaymentByOrder(Order order);
    List<Payment> findPaymentByCustomer(User cust);
    List<Payment> findPaymentByEmployee(User empl);
    List<Payment> findPaymentByCustomerId(int id);
    List<Payment> findPaymentByEmployeeId(int id);
    List<Payment> findPaymentByOrderId(int id);
}
