package com.go2it.springboot.service;

import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.Warehouse;
import com.go2it.springboot.repository.IProductRepository;
import com.go2it.springboot.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService implements IWarehouseService {
    @Autowired
    IWarehouseRepository warehouseRepository;

    @Override
    public Optional<Warehouse> findById(int id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public void save(Warehouse wh) {
        warehouseRepository.save(wh);
    }
}
