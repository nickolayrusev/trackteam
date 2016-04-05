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
	public void intTest(){
		System.out.println( 1 << 30);
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
	public void testToBinary(){
		System.out.println("binary is " + toBinary(529));
		String binary = toBinary(529);
		int gap = 0,maxGapSize = 0;
		boolean isInGap = false;
		for(int i = 0;i<binary.length();i++){
			char c = binary.charAt(i);
			if(c=='1') {
				isInGap = true;
				if(gap > maxGapSize)
					maxGapSize = gap;
				gap = 0;
				continue;
			}
			if(isInGap && c == '0')
				gap++;
		}

		System.out.println("max gap size is "+ maxGapSize);
	}

	public String toBinary(int n){
		if(n==0)
			return "0";
		if(n==1)
			return "1";
		return toBinary(n/2) + n%2;
	}

}
