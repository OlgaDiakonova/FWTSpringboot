package com.go2it.springboot.util.dtoEntityConverter;

import com.go2it.springboot.entity.Order;
import com.go2it.springboot.entity.dto.OrderDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {

    private static ModelMapper modelMapper;

    public OrderConverter() {
    }

    @Autowired
    public OrderConverter(ModelMapper modelMapper) {
        OrderConverter.modelMapper = modelMapper;
        modelMapper.addMappings(orderToDTOMapping);
        modelMapper.addMappings(DTOToOrderMapping);
    }

    PropertyMap<Order, OrderDTO> orderToDTOMapping = new PropertyMap<Order, OrderDTO>() {
        protected void configure() {
            map().setOrder_id(source.getOrder_id());
            map().setOrder_date(source.getOrder_date());
            map().setOrder_price(source.getOrder_price());
            map().setCustomerName(source.getCustomer().getFirst_name());
            map().setEmployeeName(source.getEmployee().getFirst_name());
        }
    };

    PropertyMap<OrderDTO, Order> DTOToOrderMapping = new PropertyMap<OrderDTO, Order>() {
        protected void configure() {
            map().setOrder_id(source.getOrder_id());
            map().setOrder_date(source.getOrder_date());
            map().setOrder_price(source.getOrder_price());
            map().getCustomer().setFirst_name(source.getCustomerName());
            map().getEmployee().setFirst_name(source.getEmployeeName());
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
