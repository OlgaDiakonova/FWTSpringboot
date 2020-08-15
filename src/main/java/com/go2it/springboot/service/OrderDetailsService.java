package com.go2it.springboot.service;

import com.go2it.springboot.entity.OrderDetails;
import com.go2it.springboot.repository.IOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService implements IOrderDetailsService{
    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    @Override
    public void save(OrderDetails od) {
        orderDetailsRepository.save(od);
    }

    @Override
    public List<OrderDetails> findOrderDetailsByOrderId(int id) {
        return orderDetailsRepository.findOrderDetailsByOrderId(id);
    }

}
