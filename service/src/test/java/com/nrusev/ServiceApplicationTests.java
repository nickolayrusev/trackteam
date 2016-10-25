package com.nrusev;

import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.domain.User;
import com.nrusev.service.MatchesService;
import com.nrusev.service.TeamService;
import com.nrusev.service.TeamPoolService;
import com.nrusev.service.UserService;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
@Transactional
public class ServiceApplicationTests {

	@Autowired
	MatchesService matchesService;

	@Autowired
	TeamService teamService;

	@Autowired
	UserService userService;

	@Autowired
	TeamPoolService teamPoolService;

	@Test
	public void findItalianTeams() {
		List<String> italy = matchesService.findAllTeams("italy");
		System.out.println(italy.size());
	}

	@Test
	public void findByName(){
		Team milan = teamService.findTeam("AC Milan", "Italy").orElse(null);
		Assert.notNull(milan);
	}

	@Test
	public void findMissingTeams(){
		List<String> italy = matchesService.findAllTeams("italy");
		italy.forEach(t->{
			Optional<Team> found = teamService.findTeam(t, "Italy");
			if(found.isPresent()){
				System.out.println(t + " not exists");
			}
		});
	}

	@Test
	public void findUKTeams(){
		List<Team> ukTeams = teamService.findUKTeams();
		ukTeams.forEach(System.out::println);
	}

	@Test
	public void findByCountries(){
	    teamService.findByCountries("Wales","Scotland").forEach(System.out::println);
	}
	@Test
	public void testSimilar(){
		NormalizedLevenshtein d = new NormalizedLevenshtein();
		System.out.println(d.distance("SheffieldUtd","Sheffield United"));
		System.out.println(d.distance("SheffieldWed","Sheffield Wednesday"));
	}

	@Test
	@Transactional
	public void testGetUser(){
		User nrusev = this.userService.findByUserName("nrusev");
		Set<TeamPool> teamPools = nrusev.getTeamPools();
		teamPools.forEach(System.out::println);
		teamPools.forEach(t->t.getTeams().forEach(System.out::println));
	}

	@Test
	@Rollback(false)
	public void testSaveUser(){
		Team leeds = teamService.findByTitleIgnoreCase("Leeds United").get(0);
		User nrusev = this.userService.findByUserName("nrusev");
		Set<TeamPool> teamPools = new HashSet<>(nrusev.getTeamPools());

		teamPools.stream().filter(t -> t.getName().equalsIgnoreCase("under 2.5")).findFirst().ifPresent(q->{
			q.getTeams().add(leeds);
            teamPoolService.save(q);
		});
		System.out.println("out");
	}

	@Test
	@Rollback(false)
	public void testSimpleSave(){
		User ivan = new User();
		ivan.setFirstName("ivan");
		ivan.setLastName("ivanov");
		ivan.setUserName("io");
		userService.save(ivan);
	}

}
