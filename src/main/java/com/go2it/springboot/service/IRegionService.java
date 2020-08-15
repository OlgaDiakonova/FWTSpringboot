package com.go2it.springboot.service;

import com.go2it.springboot.entity.Region;

import java.util.List;
import java.util.Optional;

public interface IRegionService {
    Optional<Region> findById(String id);
    void save(Region reg);
    List<Region> findAll();
}
