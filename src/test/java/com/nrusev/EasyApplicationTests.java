package com.nrusev;

import com.nrusev.domain.Team;
import com.nrusev.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EasyApplication.class)
@WebAppConfiguration
public class EasyApplicationTests {
	@Autowired
	TeamRepository teamRepository;


	@Test
	public void contextLoads() {
	}


	@Test
	public void testGetTeam(){
		List<Team> chelsea = teamRepository.findByTitle("Chelsea");
	}

}
