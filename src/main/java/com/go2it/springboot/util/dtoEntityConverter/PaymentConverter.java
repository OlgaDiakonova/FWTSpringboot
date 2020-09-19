package com.go2it.springboot.util.dtoEntityConverter;

import com.go2it.springboot.entity.Payment;
import com.go2it.springboot.entity.dto.PaymentDTO;
import com.go2it.springboot.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentConverter {
    private static ModelMapper modelMapper;
    @Autowired
    private OrderService orderService;

    public PaymentConverter() {
    }

    @Autowired
    public PaymentConverter(ModelMapper modelMapper) {
        PaymentConverter.modelMapper = modelMapper;
        modelMapper.addMappings(paymentToDTOMapping);
        modelMapper.addMappings(DTOToPaymentMapping);
    }

    PropertyMap<Payment, PaymentDTO> paymentToDTOMapping = new PropertyMap<Payment, PaymentDTO>() {
        protected void configure() {
            map().setPaymentId(source.getPaymentId());
            map().setPaymentDate(source.getPaymentDate());
            map().setSumPaid(source.getSumPaid());
            map().setCustomerName(source.getCustomer().getFirstName());
            map().setEmployeeName(source.getEmployee().getFirstName());
            map().setOrderId(source.getOrder().getOrderId());
            map().setOrderDate(source.getOrder().getOrderDate());
            map().setOrderPrice(source.getOrder().getOrderPrice());
        }
    };

    PropertyMap<PaymentDTO, Payment> DTOToPaymentMapping = new PropertyMap<PaymentDTO, Payment>() {
        protected void configure() {
            map().setPaymentId(source.getPaymentId());
            map().setPaymentDate(source.getPaymentDate());
            map().setSumPaid(source.getSumPaid());
            map().getCustomer().setFirstName(source.getCustomerName());
            map().getEmployee().setFirstName(source.getEmployeeName());
            //map().setOrder(orderService.findById(source.getOrderId()).get());
//            map().setOrderDate(source.getOrder().getOrderDate());
//            map().setOrderPrice(source.getOrder().getOrderPrice());

        }
    };

    public static PaymentDTO convertPaymentToDTO(Payment payment) {
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public static Payment convertDTOToPayment(PaymentDTO paymentDTO) {
        return modelMapper.map(paymentDTO, Payment.class);
    }

    public static List<PaymentDTO> convertPaymentListToDTO(List<Payment> paymentList) {
        return paymentList.stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }

    public static List<Payment> convertDTOListToPayment(List<PaymentDTO> paymentDTOList) {
        return paymentDTOList.stream()
                .map(paymentDTO -> modelMapper.map(paymentDTO, Payment.class))
                .collect(Collectors.toList());

    }
}
