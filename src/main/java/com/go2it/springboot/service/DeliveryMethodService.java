package com.go2it.springboot.service;

import com.go2it.springboot.entity.DeliveryMethod;
import com.go2it.springboot.repository.IDeliveryMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryMethodService implements IDeliveryMethodService {
    @Autowired
    private IDeliveryMethodRepository deliveryMethodRepository;

    @Override
    public Optional<DeliveryMethod> findById(int id) {
        return deliveryMethodRepository.findById(id);
    }

    @Override
    public void save(DeliveryMethod dm) {
        deliveryMethodRepository.save(dm);
    }

    @Override
    public List<DeliveryMethod> findAll() {
        return deliveryMethodRepository.findAll();
    }
}
