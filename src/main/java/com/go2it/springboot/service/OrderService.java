package com.go2it.springboot.service;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.OrderDTO;
import com.go2it.springboot.repository.IOrderRepository;
import com.go2it.springboot.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Optional<Order> findById(int id) {
        return iOrderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        iOrderRepository.save(order);
    }

    @Override
    public void save(OrderDTO orderDto) {

    }

    @Override
    public List<Order> findAll() {
        return iOrderRepository.findAll();
    }

    @Override
    public List<Order> findOrderByCustomer(User customer) {
        return iOrderRepository.findOrderByCustomer(customer);
    }

    @Override
    public List<Order> findOrderByCustomerId(int id) {
        User user = iUserRepository.findById(id).get();
        return iOrderRepository.findOrderByCustomer(user);
    }

    @Override
    public List<Order> findOrderByEmployee(User employee) {
        return iOrderRepository.findOrderByEmployee(employee);
    }

//    @Override
//    public List<Order> findOrderByOrder_date(LocalDate orderDate) {
//        return iOrderService.findOrderByOrder_date(orderDate);
//    }
}
