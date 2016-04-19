package com.nrusev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nrusev.domain.League;

public interface LeagueRepository extends CrudRepository<League, Long>{
	List<League> findByClubTrue();
}
