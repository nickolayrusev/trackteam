package com.nrusev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrusev.domain.League;
import com.nrusev.repository.LeagueRepository;

@Service
public class LeagueService {
	private final LeagueRepository leagueRepository;

	@Autowired
	public LeagueService(LeagueRepository leagueRepository){
		this.leagueRepository = leagueRepository;
	}
	
	public List<League> findAllClubLeagues(){
		List<League> findByClubTrue = leagueRepository.findByClubTrue();
		return findByClubTrue;
	}
}
