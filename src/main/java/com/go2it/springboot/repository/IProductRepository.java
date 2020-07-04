package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByWarehouse(Warehouse wh);
}
