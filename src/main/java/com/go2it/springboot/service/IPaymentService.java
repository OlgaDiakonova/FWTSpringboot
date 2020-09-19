package com.go2it.springboot.service;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.Payment;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.PaymentDTO;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    Optional<Payment> findById(int id);
    void save(Payment pmnt);
    void save(PaymentDTO pmnt);
    List<Payment> findAll();
    List<Payment> findPaymentByOrder(Order order);
    List<Payment> findPaymentByCustomer(User cust);
    List<Payment> findPaymentByEmployee(User empl);
    List<Payment> findPaymentByCustomerId(int id);
    List<Payment> findPaymentByEmployeeId(int id);
    List<Payment> findPaymentByOrderId(int id);
}
