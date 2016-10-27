package com.nrusev.service;

import com.nrusev.domain.Game;
import com.nrusev.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game findById(Long id){
        return this.gameRepository.findOne(id);
    }
}
