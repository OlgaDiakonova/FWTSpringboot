package com.go2it.springboot.service;

import com.go2it.springboot.entity.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailsService {
    void save(OrderDetails dm);
    List<OrderDetails> findOrderDetailsByOrderId(int id);

}
