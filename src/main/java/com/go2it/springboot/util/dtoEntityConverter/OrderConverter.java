package com.go2it.springboot.util.dtoEntityConverter;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.OrderDetails;
import com.go2it.springboot.entity.dto.OrderDTO;
import com.go2it.springboot.entity.dto.OrderDetailDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {

    private static ModelMapper modelMapper;
    //private OrderDetailsConverter orderDetailsConverter;

    public OrderConverter() {
    }

    @Autowired
    public OrderConverter(ModelMapper modelMapper, OrderDetailsConverter orderDetailsConverter) {
        OrderConverter.modelMapper = modelMapper;
        modelMapper.addMappings(orderToDTOMapping);
        modelMapper.addMappings(DTOToOrderMapping);
        //this.orderDetailsConverter = orderDetailsConverter;
    }

    PropertyMap<Order, OrderDTO> orderToDTOMapping = new PropertyMap<Order, OrderDTO>() {
        protected void configure() {
            map().setOrderId(source.getOrderId());
            map().setOrderDate(source.getOrderDate());
            map().setOrderPrice(source.getOrderPrice());
            map().setCustomerName(source.getCustomer().getFirstName());
            map().setEmployeeName(source.getEmployee().getFirstName());
//            List<OrderDetailDTO> odList = orderDetailsConverter.convertOrderDetailListToDTO(source.getProductOrderDetails());
//            map().setOrderDetailDTOList(odList);
        }
    };

    PropertyMap<OrderDTO, Order> DTOToOrderMapping = new PropertyMap<OrderDTO, Order>() {
        protected void configure() {
            map().setOrderId(source.getOrderId());
            map().setOrderDate(source.getOrderDate());
            map().setOrderPrice(source.getOrderPrice());
            map().getCustomer().setFirstName(source.getCustomerName());
            map().getEmployee().setFirstName(source.getEmployeeName());

        }
    };

    public static OrderDTO convertOrderToDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public static Order convertDTOToOrder(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    public static List<OrderDTO> convertOrderListToDTO(List<Order> orderList) {
        return orderList.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public static List<Order> convertDTOListToOrder(List<OrderDTO> orderDTOList) {
        return orderDTOList.stream()
                .map(orderDTO -> modelMapper.map(orderDTO, Order.class))
                .collect(Collectors.toList());

    }
}
