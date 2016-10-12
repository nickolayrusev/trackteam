package com.nrusev.service;

import com.nrusev.repository.migration.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay Rusev on 12.10.2016 г..
 */
@Service
public class MatchesService {

    final private MatchesRepository matchesRepository;

    @Autowired
    public MatchesService(MatchesRepository matchesRepository) {
        this.matchesRepository = matchesRepository;
    }

    public List<String> findAllTeams(String country){
        return this.matchesRepository.findAllTeams(country);
    }
}
