package com.nrusev;

import com.nrusev.domain.Team;
import com.nrusev.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "primaryTransactionManager")
public class RepositoryApplicationTests {

	@Autowired
	TeamRepository teamRepository;

	@Test
	public void contextLoads() {
		List<Team> allClubTeams = teamRepository.findAllClubTeams();
		System.out.println(allClubTeams.size());
	}

}
