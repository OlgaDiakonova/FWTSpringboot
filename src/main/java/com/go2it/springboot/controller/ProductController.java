package com.go2it.springboot.controller;

import com.go2it.springboot.entity.dto.ProductDTO;
import com.go2it.springboot.service.IProductService;
import com.go2it.springboot.util.dtoEntityConverter.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDTO>> getProductsFromResource() {
        try {
            return new ResponseEntity<>(ProductConverter.convertProductListToDTO(productService.findAll()), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/products")
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductDTO body){
        try {
            productService.save(body);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
