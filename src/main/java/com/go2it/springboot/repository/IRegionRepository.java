package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegionRepository extends JpaRepository<Region, String> {
}
