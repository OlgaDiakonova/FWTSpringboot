package com.go2it.springboot.service;

import com.go2it.springboot.entity.Location;
import com.go2it.springboot.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LocationService implements ILocationService {
    @Autowired
    private ILocationRepository iLocationRepository;

    @Override
    public Optional<Location> findById(int id) {
        return iLocationRepository.findById(id);
    }

    @Override
    public void save(Location location) {
        iLocationRepository.save(location);
    }

    @Override
    public List<Location> findAll() {
        return iLocationRepository.findAll();
    }

}
