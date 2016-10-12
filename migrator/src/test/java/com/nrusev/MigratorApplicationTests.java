package com.nrusev;

import com.nrusev.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MigratorApplication.class)
public class MigratorApplicationTests {


	@Autowired
	TeamRepository teamRepository;

	@Test
	public void contextLoads() {
//		matchesRepository.findAllMatches();
		System.out.println(teamRepository.findAllClubTeams());
	}

}
