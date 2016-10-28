package com.nrusev.service;

import com.nrusev.domain.Game;
import com.nrusev.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public List<Game> findAllClub(String seasonKey, String leagueKey, String country){
        return this.gameRepository.findAllClub(seasonKey,leagueKey,country);
    }

    public List<Game> findTodaysGames(){
        return this.gameRepository.findAllClub("2014/15","en","England").stream().filter(g->g.getRound().getTitle().equalsIgnoreCase("Matchday 38")).collect(toList());
    }
}
