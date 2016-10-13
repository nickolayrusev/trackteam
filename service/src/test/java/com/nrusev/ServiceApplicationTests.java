package com.nrusev;

import com.nrusev.domain.Team;
import com.nrusev.migration.Match;
import com.nrusev.repository.migration.MatchesRepository;
import com.nrusev.service.MatchesService;
import com.nrusev.service.TeamService;
import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ServiceApplicationTests {

	@Autowired
	MatchesService matchesService;

	@Autowired
	TeamService teamService;

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
	public void testSimilar(){
		NormalizedLevenshtein d = new NormalizedLevenshtein();
		System.out.println(d.distance("SheffieldUtd","Sheffield United"));
		System.out.println(d.distance("SheffieldWed","Sheffield Wednesday"));
	}
}
