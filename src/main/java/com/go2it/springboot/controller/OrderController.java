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
    public String showOrderByOrderId(@PathVariable String id) {
        OrderDTO orderDto = new OrderDTO();
        orderService.findById(Integer.valueOf(id)).ifPresent(order -> {
            orderDto.setOrder_date(order.getOrder_date());
            orderDto.setOrder_price(order.getOrder_price());
            orderDto.setOrder_id(order.getOrder_id());
            orderDto.setCustomer(order.getCustomer());
        });

        String orderInfo = "<div style=\"text-align:left;\">" + "<h1>Order #: " + orderDto.getOrder_id() + " " +
                "ordered at " + orderDto.getOrder_date() +  " " +
                "ordered by " + orderDto.getCustomer().getFirst_name() + " " +
                "order price is " + orderDto.getOrder_price() + "</h1>" + "</div>";

        String htmlText = "<div style=\"text-align:center;\">" + "<h1>Order information: </h1>" + "</div>" + orderInfo;

        return htmlText;

    }

    @RequestMapping(value = "/user/{id}/orders/", method = RequestMethod.GET)
    public String showOrdersByCustomerId(@PathVariable String id) {
        List<OrderDTO> orderDtoList = new ArrayList<>();
        String orderInfo = "";
        AtomicReference<User> cust = new AtomicReference<>(new User());

        userService.findById(Integer.valueOf(id)).ifPresent(customer -> cust.set(customer));

        for (Order item : orderService.findOrderByCustomer(cust.get())) {
            OrderDTO tempOrderDto = new OrderDTO();
            tempOrderDto.setOrder_date(item.getOrder_date());
            tempOrderDto.setOrder_price(item.getOrder_price());
            tempOrderDto.setOrder_id(item.getOrder_id());
            tempOrderDto.setCustomer(item.getCustomer());
            orderDtoList.add(tempOrderDto);
        }

        for (OrderDTO orderDto : orderDtoList) {
            orderInfo += "<li>" + orderDto.getOrder_id() + " " +
                    orderDto.getOrder_date() + " " +
                    orderDto.getOrder_price() + "</li>";
        }

        String htmlText = "<div style=\"text-align:center;\">" + "<h1>Order information: </h1>" + "</div>" + orderInfo;

        return htmlText;

    }
}
