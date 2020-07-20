package com.go2it.springboot.util.dtoEntityConverter;

import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    private static ModelMapper modelMapper;

    public ProductConverter() {
    }

    @Autowired
    public ProductConverter(ModelMapper modelMapper) {
        ProductConverter.modelMapper = modelMapper;
        modelMapper.addMappings(productToDTOMapping);
        modelMapper.addMappings(DTOToProductMapping);
    }

    PropertyMap<Product, ProductDTO> productToDTOMapping = new PropertyMap<Product, ProductDTO>() {
        @Override
        protected void configure() {
            map().setName(source.getProductName());
            map().setDescription(source.getDescription());
            map().setPrice(source.getPrice());
        }
    };

    PropertyMap<ProductDTO, Product> DTOToProductMapping = new PropertyMap<ProductDTO, Product>() {
        @Override
        protected void configure() {
            map().setProductName(source.getName());
            map().setDescription(source.getDescription());
            map().setPrice(source.getPrice());
        }
    };

    public static ProductDTO convertProductToDTO(Product prod) {
        return modelMapper.map(prod, ProductDTO.class);
    }

    public static Product convertDTOToProduct(ProductDTO prodDTO) {
        return modelMapper.map(prodDTO, Product.class);
    }

    public static List<ProductDTO> convertProductListToDTO(List<Product> productList) {
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public static List<Product> convertDTOListToProduct(List<ProductDTO> productDTOList) {
        return productDTOList.stream()
                .map(prodDTO -> modelMapper.map(prodDTO, Product.class))
                .collect(Collectors.toList());

    }
}
