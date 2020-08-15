package com.go2it.springboot.service;

import com.go2it.springboot.entity.Location;

import java.util.List;
import java.util.Optional;

public interface ILocationService {
    Optional<Location> findById(int id);
    void save(Location location);
    List<Location> findAll();
}
