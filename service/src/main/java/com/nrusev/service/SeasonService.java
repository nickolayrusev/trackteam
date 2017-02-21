package com.nrusev.service;

import com.nrusev.domain.Season;
import com.nrusev.enums.SeasonKeys;
import com.nrusev.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nikolay Rusev on 21.2.2017 г..
 */
@Service
public class SeasonService {
    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public List<Season> findByTitle(SeasonKeys season){
        String seasonString = Arrays.stream(season.getKey().split("/")).reduce("", (s, t) -> {
            if(!s.isEmpty())
                return s + "/" + t.substring(2,4)  ;
            else
                return s+t;
        });
       return this.seasonRepository.findByTitle(seasonString) ;
    }

}
