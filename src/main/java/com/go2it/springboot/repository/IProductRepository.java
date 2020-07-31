package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByWarehouse(Warehouse wh);
    Optional<Product> findProductByProductName(String name);
}
