package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepository extends JpaRepository<Location, Integer> {
}
