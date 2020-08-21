package com.go2it.springboot.service;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.OrderDTO;
import com.go2it.springboot.repository.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository iOrderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Override
    public Optional<Order> findById(int id) {
        return iOrderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        logger.info("Starting writing to order DB save(order = {})", order);
        try {
            iOrderRepository.save(order);
            logger.info("Finish writing order to DB save(order = {})", order);
        }catch (RuntimeException e){
            logger.error("Repo threw exception while save( order = {}, and caused: {}", order, e.toString());
        }
    }

    @Override
    public void save(OrderDTO orderDto) {
        logger.info("Starting writing to order DB save(order = {})", orderDto);
        try {
            iOrderRepository.save(orderDto);
            logger.info("Finish writing order to DB save(order = {})", orderDto);
        }catch (RuntimeException e){
            logger.error("Repo threw exception while save( order = {}, and caused: {}", orderDto, e.toString());
        }
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

        return iOrderRepository.findOrderByCustomerId(id);
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
