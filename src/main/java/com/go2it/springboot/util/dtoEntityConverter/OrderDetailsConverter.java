package com.go2it.springboot.util.dtoEntityConverter;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.OrderDetails;
import com.go2it.springboot.entity.Product;
import com.go2it.springboot.entity.dto.OrderDetailDTO;
import com.go2it.springboot.service.IOrderService;
import com.go2it.springboot.service.IProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailsConverter {

    private static ModelMapper modelMapper;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IProductService iProductService;

    public OrderDetailsConverter() {
    }

    @Autowired
    public OrderDetailsConverter(ModelMapper modelMapper) {
        OrderDetailsConverter.modelMapper = modelMapper;
        modelMapper.addMappings(orderDetailsToDTOMapping);
        modelMapper.addMappings(DTOToorderDetailsMapping);
    }

    PropertyMap<OrderDetails, OrderDetailDTO> orderDetailsToDTOMapping = new PropertyMap<OrderDetails, OrderDetailDTO>() {
        protected void configure() {
            map().setOrderId(source.getOrder().getOrderId());
            map().setProductName(source.getProduct().getProductName());
            map().setWeight(source.getWeight());
            map().setTotalPrice(source.getTotalPrice());
        }
    };

    PropertyMap<OrderDetailDTO, OrderDetails> DTOToorderDetailsMapping = new PropertyMap<OrderDetailDTO, OrderDetails>() {
        protected void configure() {

//            iOrderService.findById(source.getOrderId()).ifPresent(orderToFind -> map().setOrder(orderToFind));
//            iProductService.findProductByProductName(source.getProductName()).ifPresent(product -> map().setProduct(product));
            map().setWeight(source.getWeight());
            map().setTotalPrice(source.getTotalPrice());
        }
    };

    public static OrderDetailDTO convertOrderToDTO(OrderDetails orderDetails) {
        return modelMapper.map(orderDetails, OrderDetailDTO.class);
    }

    public static OrderDetails convertDTOToOrder(OrderDetailDTO orderDetailDTO) {
        return modelMapper.map(orderDetailDTO, OrderDetails.class);
    }

    public static List<OrderDetailDTO> convertOrderDetailListToDTO(List<OrderDetails> orderDetailsList) {
        return orderDetailsList.stream()
                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailDTO.class))
                .collect(Collectors.toList());
    }

    public static List<OrderDetails> convertDTOListToOrderDetail(List<OrderDetailDTO> orderDetailsDTOList) {
        return orderDetailsDTOList.stream()
                .map(orderDetailDTO -> modelMapper.map(orderDetailDTO, OrderDetails.class))
                .collect(Collectors.toList());

    }
}
