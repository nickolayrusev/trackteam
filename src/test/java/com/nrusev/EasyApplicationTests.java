package com.nrusev;

import com.nrusev.domain.Country;
import com.nrusev.domain.Team;
import com.nrusev.repository.CountryRepository;
import com.nrusev.repository.LeagueRepository;
import com.nrusev.repository.TeamRepository;
import com.nrusev.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EasyApplication.class)
@WebAppConfiguration
public class EasyApplicationTests {
	@Autowired
	TeamRepository teamRepository;

	@Autowired
	TeamService teamService;

	@Autowired
	LeagueRepository leagueRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testGetTeam(){
		List<Team> chelsea = teamRepository.findByTitle("Chelsea");
		System.out.println(chelsea.size());
	}

	@Test
	public void testGetClubTeams(){
		System.out.println(teamService.findAllClubTeams().size());
	}

	@Test
	public void testEuropeanTeams(){
//		teamRepository.findByCountryContinentNameAndNationalIsFalseAndClubIsFalse("Europe");
		List<Team> europe = teamRepository.findByContinentName("Europe", false, true);
		System.out.println(europe.size());
	}

	@Test
	public void testAllTeams(){
//		teamRepository.findByCountryContinentNameAndNationalIsFalseAndClubIsFalse("Europe");
		List<Team> all = teamRepository.findAll();
		System.out.println(all.size());
	}
	
	@Test
	public void testFindBySeason(){
		System.out.println("season 1");
		teamRepository.findBySeasonKeyAndLeagueKey("2014/15","en").forEach(t->{System.out.println(t.getTitle());});
		System.out.println("season 2");
		teamRepository.findBySeasonKeyAndLeagueKey("2013/14","en").forEach(t->{System.out.println(t.getTitle());});
	}

	@Test
	public void testFindAllLeagues(){
		leagueRepository.findByClubTrue().forEach(System.out::println);
	}
	
	@Test
	public void testFindByDistinctCountries(){
		List<Country> findAvailableCountries = countryRepository.findAvailableCountries();
		findAvailableCountries.forEach(System.out::println);
		for (Country country : findAvailableCountries) {
			System.out.println(country.getLeagues().size());
		}
	}
}
