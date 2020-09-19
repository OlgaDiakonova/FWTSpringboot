package com.go2it.springboot.controller;


import com.go2it.springboot.entity.dto.PaymentDTO;
import com.go2it.springboot.entity.dto.ProductDTO;
import com.go2it.springboot.service.IPaymentService;
import com.go2it.springboot.service.IProductService;
import com.go2it.springboot.util.dtoEntityConverter.PaymentConverter;
import com.go2it.springboot.util.dtoEntityConverter.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    IPaymentService paymentService;

    @GetMapping(value = "/payments")
    public ResponseEntity<List<PaymentDTO>> getPaymentsFromResource() {
        try {
            return new ResponseEntity<>(PaymentConverter.convertPaymentListToDTO(paymentService.findAll()), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/payments")
    public ResponseEntity<HttpStatus> createPayment(@RequestBody PaymentDTO body){
        try {
            paymentService.save(body);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
