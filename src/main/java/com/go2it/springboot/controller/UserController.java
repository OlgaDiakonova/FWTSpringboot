package com.go2it.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.User;
import com.go2it.springboot.entity.dto.OrderDTO;
import com.go2it.springboot.entity.dto.PaymentDTO;
import com.go2it.springboot.entity.dto.UserDTO;
import com.go2it.springboot.service.IRoleService;
import com.go2it.springboot.service.IUserService;
import com.go2it.springboot.service.OrderService;
import com.go2it.springboot.service.PaymentService;
import com.go2it.springboot.util.dtoEntityConverter.OrderConverter;
import com.go2it.springboot.util.dtoEntityConverter.PaymentConverter;
import com.go2it.springboot.util.dtoEntityConverter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/user")
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

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> showUserLogin(@PathVariable String id) {

        try {
            return new ResponseEntity<>(UserConverter.convertUserToDTO(userService.findById(Integer.valueOf(id)).get()), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/users/{id}/order", method = RequestMethod.POST)
    public ResponseEntity<String> createOrder(@RequestBody String orderDTOJson, @PathVariable int id) throws IOException {
        if (orderDTOJson == null || orderDTOJson.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = objectMapper.readValue(orderDTOJson, OrderDTO.class);
        if (orderDTO != null) {
            Order order = OrderConverter.convertDTOToOrder(orderDTO);
            userService.findById(id).ifPresent(customer -> {
                order.setCustomer(customer);
            });
            orderService.save(order);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value = "/users/{id}/orders")
    public ResponseEntity<List<OrderDTO>> showOrdersByCustomerId(@PathVariable String id) {
       try{
           return new ResponseEntity<>(OrderConverter.convertOrderListToDTO(orderService.findOrderByCustomerId(Integer.valueOf(id))), HttpStatus.OK);
       }catch (RuntimeException e){
           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }

    @GetMapping(value = "/users/{id}/payments")
    public ResponseEntity<List<PaymentDTO>> showPaymentsByCustomerId(@PathVariable String id) {
        try{
            return new ResponseEntity<>(PaymentConverter.convertPaymentListToDTO(paymentService.findPaymentByCustomerId(Integer.valueOf(id))), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
