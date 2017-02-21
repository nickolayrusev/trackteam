package com.nrusev.repository;

import com.nrusev.domain.League;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeagueRepository extends CrudRepository<League, Long>{
	List<League> findByClubTrue();
	List<League> findByTitle(String title);
}
