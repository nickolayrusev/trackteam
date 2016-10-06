package com.nrusev;

import com.nrusev.domain.Team;
import com.nrusev.repository.TeamRepository;
import com.nrusev.repository.migration.MatchesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryApplicationTests {

	@Autowired
	TeamRepository teamRepository;

	@Autowired
    MatchesRepository matchesRepository;

	@Test
	public void contextLoads() {
		List<Team> allClubTeams = teamRepository.findAllClubTeams();
        Assert.isTrue(allClubTeams.size() > 1000);
    }


	@Test
	public void matchesCount() {
        System.out.println(matchesRepository.countAll());
	}


	@Test
	public void testFindAllMatches(){
		matchesRepository.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindAllMatchesForCountry(){
		matchesRepository.findAll("italy").forEach(System.out::println);
	}


}
