package com.go2it.springboot.service;

import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.Warehouse;
import com.go2it.springboot.entity.dto.ProductDTO;
import com.go2it.springboot.repository.IProductRepository;
import com.go2it.springboot.util.dtoEntityConverter.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findProductByProductName(String name) {
        return productRepository.findProductByProductName(name);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void save(ProductDTO productDto){
        Product product = ProductConverter.convertDTOToProduct(productDto);
        save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByWarehouse(Warehouse wh) {
        return productRepository.findAllByWarehouse(wh);
    }


}
