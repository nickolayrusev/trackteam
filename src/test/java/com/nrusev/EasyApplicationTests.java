package com.nrusev;

import com.nrusev.domain.Team;
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
}
