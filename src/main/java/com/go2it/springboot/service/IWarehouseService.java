package com.go2it.springboot.service;

import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.Warehouse;

import java.util.Optional;

public interface IWarehouseService {
    Optional<Warehouse> findById(int id);
    void save(Warehouse wh);
}
