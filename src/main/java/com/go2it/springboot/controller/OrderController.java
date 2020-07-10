package com.go2it.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.service.OrderService;
import com.go2it.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}/order", method = RequestMethod.POST)
    public ResponseEntity<String> createOrder(@RequestBody String orderJson, @PathVariable int id) throws IOException {
        if (orderJson == null || orderJson.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(orderJson, Order.class);
        if (order != null) {
            userService.findById(id).ifPresent(customer -> {
                order.setCustomer(customer);
            });
            orderService.save(order);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @RequestMapping(value = "/user/orders/{id}", method = RequestMethod.GET)
    public String showOrderById(@PathVariable String id) {
        AtomicReference<String> orderInfo = new AtomicReference<>("");
        orderService.findById(Integer.valueOf(id)).ifPresent(order -> {
             orderInfo.set("<div style=\"text-align:left;\">" + "<h1>Order #: " + order.getOrder_id() + " " +
                     "ordered at " + order.getOrder_date() +  " " +
                     "ordered by " + order.getCustomer().getFirst_name() + " " +
                     "order price is " + order.getOrder_price() + "</h1>" + "</div>");
        });

        String htmlText = "<div style=\"text-align:center;\">" + "<h1>Order information: </h1>" + "</div>" + orderInfo;

        return htmlText;

    }
}
