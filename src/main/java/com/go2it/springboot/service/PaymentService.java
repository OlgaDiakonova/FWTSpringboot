package com.go2it.springboot.service;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.Payment;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.PaymentDTO;
import com.go2it.springboot.entity.dto.UserDTO;
import com.go2it.springboot.repository.IOrderRepository;
import com.go2it.springboot.repository.IPaymentRepository;
import com.go2it.springboot.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository paymentRepository;
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<Payment> findById(int id) {
        return paymentRepository.findById(id);
    }

    @Override
    public void save(Payment pmnt) {
        paymentRepository.save(pmnt);
    }

    @Override
    public void save(PaymentDTO paymentDto) {
        Payment pmnt = new Payment();
        pmnt.setOrder(orderRepository.findById(paymentDto.getOrderId()).get());
        pmnt.setCustomer(orderRepository.findById(paymentDto.getOrderId()).get().getCustomer());
        pmnt.setEmployee(orderRepository.findById(paymentDto.getOrderId()).get().getEmployee());
        save(pmnt);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> findPaymentByOrder(Order order) {
        return paymentRepository.findPaymentByOrder(order);
    }

    @Override
    public List<Payment> findPaymentByCustomer(User cust) {
        return paymentRepository.findPaymentByCustomer(cust);
    }

    @Override
    public List<Payment> findPaymentByEmployee(User empl) {
        return paymentRepository.findPaymentByEmployee(empl);
    }

    @Override
    public List<Payment> findPaymentByCustomerId(int id) {
        return paymentRepository.findPaymentByCustomerId(id);
    }

    @Override
    public List<Payment> findPaymentByEmployeeId(int id) {
        return paymentRepository.findPaymentByEmployeeId(id);
    }

    @Override
    public List<Payment> findPaymentByOrderId(int id) {
        return paymentRepository.findPaymentByOrderId(id);
    }

}
