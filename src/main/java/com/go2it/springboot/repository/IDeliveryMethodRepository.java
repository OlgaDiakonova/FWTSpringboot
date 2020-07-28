package com.go2it.springboot.repository;

import com.go2it.springboot.entity.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeliveryMethodRepository extends JpaRepository<DeliveryMethod, Integer> {
}
