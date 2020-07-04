package com.go2it.springboot.controller;

import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.dto.ProductDto;
import com.go2it.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    IProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProductsFromResource() {
        List<Product> productList = productService.findAll();
        String htmlText = "<div style=\"text-align:center;\">" + "<h1>In our store you find the biggest variety of different kind of fish </h1>" + "</div>";
        htmlText += "<ul>";

        for (Product product : productList) {
            htmlText += "<li>"+ product.getProduct_name() + "</li>";
        };

        htmlText += "</ul>";

        return htmlText;

    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<String> createProduct(@RequestBody ProductDto body){
        productService.save(body);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
