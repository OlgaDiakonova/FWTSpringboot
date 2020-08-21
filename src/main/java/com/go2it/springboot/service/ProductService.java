package com.go2it.springboot.service;

import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.Warehouse;
import com.go2it.springboot.entity.dto.ProductDTO;
import com.go2it.springboot.repository.IProductRepository;
import com.go2it.springboot.util.dtoEntityConverter.ProductConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

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
        logger.info("Starting writing to product DB save(productDto = {})", productDto);
        try {
            Product product = ProductConverter.convertDTOToProduct(productDto);
            save(product);
            logger.info("Finish writing product to DB save(productDto = {})", productDto);
        }catch (RuntimeException e){
            logger.error("Repo threw exception while save( productDto = {}, and caused: {}", productDto, e.toString());
        }
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
