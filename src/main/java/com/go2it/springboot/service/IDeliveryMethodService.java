package com.go2it.springboot.service;

import com.go2it.springboot.entity.DeliveryMethod;

import java.util.List;
import java.util.Optional;

public interface IDeliveryMethodService {
    Optional<DeliveryMethod> findById(int id);
    void save(DeliveryMethod dm);
    List<DeliveryMethod> findAll();
}
