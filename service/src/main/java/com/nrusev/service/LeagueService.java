package com.nrusev.service;

import com.nrusev.domain.League;
import com.nrusev.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueService {
	private final LeagueRepository leagueRepository;

	@Autowired
	public LeagueService(LeagueRepository leagueRepository){
		this.leagueRepository = leagueRepository;
	}
	
	public List<League> findAllClubLeagues(){
		return leagueRepository.findByClubTrue();
	}

	public List<League> findByLeagueTitle(String title){
		return this.leagueRepository.findByTitle(title);
	}
}
