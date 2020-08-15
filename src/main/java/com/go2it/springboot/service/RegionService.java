package com.go2it.springboot.service;

import com.go2it.springboot.entity.Region;
import com.go2it.springboot.repository.IRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RegionService implements IRegionService {
    @Autowired
    private IRegionRepository iRegionRepository;

    @Override
    public Optional<Region> findById(String id) {
        return iRegionRepository.findById(id);
    }

    @Override
    public void save(Region reg) {
        iRegionRepository.save(reg);
    }

    @Override
    public List<Region> findAll() {
        return iRegionRepository.findAll();
    }
}
