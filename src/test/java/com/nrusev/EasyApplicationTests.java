package com.nrusev;

import com.nrusev.domain.Pool;
import com.nrusev.domain.Team;
import com.nrusev.repository.PoolRepository;
import com.nrusev.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EasyApplication.class)
@WebAppConfiguration
public class EasyApplicationTests {
	@Autowired
	TeamRepository teamRepository;

	@Autowired
	PoolRepository poolRepository;

	@Test
	public void contextLoads() {
	}


	@Test
	public void testGetTeam(){
		List<Team> chelsea = teamRepository.findByName("Chelsea");
	}

	@Test
	public void testSaveTeam(){
		IntStream.range(1,10).forEach(i->{
			Team team = new Team();
			team.setName("Chelsea");
			teamRepository.save(team);
		});
	}

	@Test
	public void testSavePool(){
		Pool pool = new Pool();
		pool.setName("under over 2.5");
		Team chelsea = teamRepository.findByName("Chelsea").get(0);
		System.out.println("chelsea is : " + chelsea );
		pool.getTeams().add(chelsea);
		poolRepository.save(pool);
	}
}
