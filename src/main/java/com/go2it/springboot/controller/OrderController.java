package com.go2it.springboot.controller;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.dto.OrderDTO;
import com.go2it.springboot.entity.dto.OrderDetailDTO;
import com.go2it.springboot.entity.dto.PaymentDTO;
import com.go2it.springboot.service.OrderDetailsService;
import com.go2it.springboot.service.OrderService;
import com.go2it.springboot.service.PaymentService;
import com.go2it.springboot.util.dtoEntityConverter.OrderConverter;
import com.go2it.springboot.util.dtoEntityConverter.OrderDetailsConverter;
import com.go2it.springboot.util.dtoEntityConverter.PaymentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = {"/orders/{id}"})
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable String id) {
        try {
//            return new ResponseEntity<>(OrderConverter.convertOrderToDTO(orderService.findById(Integer.valueOf(id)).get()), HttpStatus.OK);
            return new ResponseEntity<>(orderService.findById(Integer.valueOf(id)).get(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/orders/{id}/order_details"})
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByOrderId(@PathVariable String id) {
        try {
            return new ResponseEntity<>(OrderDetailsConverter.convertOrderDetailListToDTO(orderDetailsService.findOrderDetailsByOrderId(Integer.valueOf(id))), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/orders/{id}/payments")
    public ResponseEntity<List<PaymentDTO>> showPaymentsByCustomerId(@PathVariable String id) {
        try{
            return new ResponseEntity<>(PaymentConverter.convertPaymentListToDTO(paymentService.findPaymentByCustomerId(Integer.valueOf(id))), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
