package com.nrusev;

import com.nrusev.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.StringTokenizer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MigratorApplication.class)
public class MigratorApplicationTests {
	private static final String COMPETENCY_CATEGORY = "COMPETENCY_CATEGORY";


	@Autowired
	TeamRepository teamRepository;

	@Test
	public void contextLoaded() {
//		matchesRepository.findAllMatches();
		System.out.println(teamRepository.findAllClubTeams());
	}
	@Test
	public void contextLoads() {
		String domain = "COMPETENCY_CATEGORY_PRO_ANA_ADVANA";

		StringTokenizer parentsTokens = new StringTokenizer(
				domain.substring(COMPETENCY_CATEGORY.length()),
				"_");

		System.out.println(parentsTokens.countTokens());

	}

}
