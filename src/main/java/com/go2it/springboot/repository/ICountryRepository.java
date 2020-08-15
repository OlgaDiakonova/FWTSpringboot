package com.go2it.springboot.repository;

import com.go2it.springboot.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountryRepository extends JpaRepository<Country, String> {
}
