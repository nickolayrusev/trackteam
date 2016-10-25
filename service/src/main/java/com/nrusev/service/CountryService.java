package com.nrusev.service;

import com.nrusev.domain.Country;
import com.nrusev.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay Rusev on 12.10.2016 г..
 */
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findByName(String name){
        return this.countryRepository.findByName(name);
    }

    public List<Country> findAll(){
        return this.countryRepository.findAvailableCountries();
    }

}
