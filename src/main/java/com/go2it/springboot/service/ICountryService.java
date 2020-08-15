package com.go2it.springboot.service;

import com.go2it.springboot.entity.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    Optional<Country> findById(String id);
    void save(Country country);
    List<Country> findAll();
}
