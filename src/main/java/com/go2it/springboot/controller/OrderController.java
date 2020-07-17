package com.go2it.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.OrderDTO;
import com.go2it.springboot.service.OrderService;
import com.go2it.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String showOrderByOrderId(@PathVariable String id) {
        OrderDTO orderDto = new OrderDTO();
        orderService.findById(Integer.valueOf(id)).ifPresent(order -> {
            orderDto.setOrder_date(order.getOrder_date());
            orderDto.setOrder_price(order.getOrder_price());
            orderDto.setOrder_id(order.getOrder_id());
            orderDto.setCustomerName(order.getCustomer().getFirst_name() + " " + order.getCustomer().getLast_name());
        });

        String orderInfo = "<div style=\"text-align:left;\">" + "<h1>Order #: " + orderDto.getOrder_id() + " " +
                "ordered at " + orderDto.getOrder_date() +  " " +
                "ordered by " + orderDto.getCustomerName() + " " +
                "order price is " + orderDto.getOrder_price() + "</h1>" + "</div>";

        String htmlText = "<div style=\"text-align:center;\">" + "<h1>Order information: </h1>" + "</div>" + orderInfo;

        return htmlText;

    }

}
