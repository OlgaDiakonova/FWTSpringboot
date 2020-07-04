package com.go2it.springboot.service;


import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.Warehouse;
import com.go2it.springboot.entity.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<Product> findById(int id);
    void save(Product product);
    void save(ProductDto productDto);
    List<Product> findAll();
    List<Product> findAllByWarehouse(Warehouse wh);
}
