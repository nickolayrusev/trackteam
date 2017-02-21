package com.nrusev.service;

import com.nrusev.domain.Event;
import com.nrusev.domain.Round;
import com.nrusev.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay Rusev on 17.2.2017 г..
 */
@Service
public class RoundService {
    private final RoundRepository roundRepository;

    @Autowired
    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }


    public Round save(Round round){
        return this.roundRepository.save(round);
    }

    public List<Round> findByEventAndPosition(Event event, Long pos){
        return this.roundRepository.findByEventAndPos(event, pos);
    }
}
