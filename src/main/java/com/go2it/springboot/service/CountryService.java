package com.go2it.springboot.service;

import com.go2it.springboot.entity.Country;
import com.go2it.springboot.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements ICountryService {
    @Autowired
    private ICountryRepository iCountryRepository;

    @Override
    public Optional<Country> findById(String id) {
        return iCountryRepository.findById(id);
    }

    @Override
    public void save(Country country) {
        iCountryRepository.save(country);
    }

    @Override
    public List<Country> findAll() {
        return iCountryRepository.findAll();
    }
}
