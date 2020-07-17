package com.go2it.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.Role;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.OrderDTO;
import com.go2it.springboot.entity.dto.UserDTO;
import com.go2it.springboot.service.IRoleService;
import com.go2it.springboot.service.IUserService;
import com.go2it.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody String userJson) throws IOException {

        if (userJson == null || userJson.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        User customer = objectMapper.readValue(userJson, User.class);
        if (customer != null) {
            roleService.findById(1).ifPresent(role -> {
                customer.setRole(role);
            });
            userService.save(customer);

            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String showUserLogin(@PathVariable String id) {
        UserDTO userDto = new UserDTO();
        String userFullName = "";
//        Optional<User> user = userService.findById(Integer.valueOf(id));
        userService.findById(Integer.valueOf(id)).ifPresent(user -> {
            userDto.setFirst_name(user.getFirst_name());
            userDto.setLast_name(user.getLast_name());
        });

        if(userDto.getFirst_name() == null && userDto.getLast_name() == null){
            userFullName = "Unknown User";
        }else {
            userFullName = userDto.getFirst_name() + " " + userDto.getLast_name();
        }
        String htmlText = "<div style=\"text-align:center;\">" + "<h1>You are logged in as " + userFullName + "</h1>" + "</div>";

        return htmlText;

    }

    @RequestMapping(value = "/users/{id}/order", method = RequestMethod.POST)
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

    @RequestMapping(value = "/users/{id}/orders", method = RequestMethod.GET)
    public String showOrdersByCustomerId(@PathVariable String id) {
        List<OrderDTO> orderDtoList = new ArrayList<>();
        String orderInfo = "";
        AtomicReference<User> cust = new AtomicReference<>(new User());

        userService.findById(Integer.valueOf(id)).ifPresent(customer -> cust.set(customer));

        if (cust.get().getUser_id() != 0) {
            for (Order item : cust.get().getCustomerOrders()) {
                OrderDTO tempOrderDto = new OrderDTO();
                tempOrderDto.setOrder_date(item.getOrder_date());
                tempOrderDto.setOrder_price(item.getOrder_price());
                tempOrderDto.setOrder_id(item.getOrder_id());
                tempOrderDto.setCustomerName(item.getCustomer().getFirst_name() + " " + item.getCustomer().getLast_name());
                orderDtoList.add(tempOrderDto);
            }

            for (OrderDTO orderDto : orderDtoList) {
                orderInfo += "<li>" + orderDto.getOrder_id() + " " +
                        orderDto.getOrder_date() + " " +
                        orderDto.getOrder_price() + "</li>";
            }
        } else {
            orderInfo = "There are no orders for this customer yet!";
        }

        String htmlText = "<div style=\"text-align:center;\">" + "<h1>Order information: </h1>" + "</div>" + orderInfo;

        return htmlText;

    }


}
