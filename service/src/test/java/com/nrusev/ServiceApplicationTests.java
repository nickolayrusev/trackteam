package com.nrusev;

import antlr.collections.impl.IntRange;
import com.nrusev.domain.Country;
import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.domain.User;
import com.nrusev.enums.LeagueKeys;
import com.nrusev.enums.SeasonKeys;
import com.nrusev.exchange.DataExchanger;
import com.nrusev.exchange.impl.GameDto;
import com.nrusev.service.*;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ServiceApplication.class})
public class ServiceApplicationTests {

	@Autowired
	MatchesService matchesService;

	@Autowired
	TeamService teamService;

	@Autowired
	UserService userService;

	@Autowired
	TeamPoolService teamPoolService;

	@Autowired
	GameService gameService;

	@Autowired
	CountryService countryService;

//	@Autowired @Qualifier("betfairExchanger")
//	DataExchanger dataExchanger;

	@Autowired @Qualifier("xmlSoccerExchanger")
	DataExchanger xmlSoccerExchanger;

	@Test
	public void findItalianTeams() {
		List<String> italy = matchesService.findAllTeams("france");
		System.out.println(italy.size());
		italy.forEach(System.out::println);

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
			found.ifPresent(team -> System.out.println(t + " not exists"));
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
		Optional<User> nrusev = this.userService.findByUserName("nrusev");
//		Set<TeamPool> teamPools = nrusev.getTeamPools();
//		teamPools.forEach(System.out::println);
//		teamPools.forEach(t->t.getTeams().forEach(System.out::println));
	}

	@Test
	@Rollback(false)
	public void testSaveUser(){
		Team leeds = teamService.findByTitleIgnoreCase("Leeds United").get(0);
		Optional<User> nrusev = this.userService.findByUserName("nrusev");
//		Set<TeamPool> teamPools = new HashSet<>(nrusev.getTeamPools());
//
//		teamPools.stream().filter(t -> t.getName().equalsIgnoreCase("under 2.5")).findFirst().ifPresent(q->{
//			q.getTeams().add(leeds);
//            teamPoolService.save(q);
//		});
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

	@Test
	public void testFindPoolsByUserName(){
		teamPoolService.findAllByUserName("nrusev");
	}

	@Test
	@Transactional
	public void testFindGameById(){
		Game byId = gameService.findById(4775L);
		System.out.println(byId.getHomeTeam() + " " + byId.getVisitorTeam() + " " + byId.getRound().getTitle());
	}

	@Test
	public void testFindAll(){
		gameService.findAllClub("2014/15","en","England").forEach(g-> {
			System.out.println( g.getPlayAt() + " " + g.getHomeTeam().getTitle() + " vs. " + g.getVisitorTeam().getTitle() + " " + g.getRound().getTitle() +" " + g.getScore1() + ":" + g.getScore2());
		});
	}

	@Test
	public void testFindHeadToHead(){
		gameService.findAllHeadToHead("Manchester United","Manchester City").forEach(System.out::println);
	}

	@Test
	public void testUrl() throws UnsupportedEncodingException {
		String param = "Ð´Ð¾Ð±ÑÐµ";
		String japanparam = "æ\u0099\u0082ã\u0081\u0096ã\u0081¡ã\u0082\u0088ã\u0082\u008Bè¶³9ä¸\u0096ã\u0083\u0083ã\u0081\u009Aã\u0081\u008Dé\u009B»";
		System.out.println(new String(param.getBytes("ISO-8859-1"), "UTF-8"));
		System.out.println(new String(japanparam.getBytes("ISO-8859-1"), "UTF-8"));
	}

//	@Test
//	public void testBetfairClient(){
//		dataExchanger.findTodayGames().forEach(System.out::println);
//	}

	@Test
	public void testXmlSoccerClient(){
		List<GameDto> todayGames = xmlSoccerExchanger.findTodayGames();
		todayGames.forEach(System.out::println);
	}

	@Test
	public void testPagingAndSortingTeams(){
		teamService.findAll(0,10).forEach(System.out::println);
		System.out.println("--------------------------------------------");
		teamService.findAll(1,10).forEach(System.out::println);
	}

	@Test
	public void testPagingAndSortingGames(){
		gameService.findGamesByTeam(1006L,0,10).forEach(System.out::println);
	}

	@Test
	@Rollback(false)
	public void testSaveTeam() {
		Country scotland = this.countryService.findByName("Scotland").get(0);
		String name = "Rangers";
		Team newTeam = new Team();
		newTeam.setCountry(scotland);
		newTeam.setClub(true);
		newTeam.setTitle(name);
		newTeam.setKey(name.toLowerCase());
		newTeam.setNational(false);
		newTeam.setCode(name.substring(0, 3).toUpperCase());
		teamService.save(newTeam);
	}


	@Test
	@Rollback(false)
	public void testDeleteTeam(){
	    this.teamService.findTeam("Rangers","Scotland").ifPresent(t->{
	    	this.teamService.delete(t);
		});
	}

	@Test
	public void saveGame(){

		List<GameDto> todayGames = xmlSoccerExchanger.getFixturesByLeagueAndSeason(LeagueKeys.SCOTTISH_PREMIER_LEAGUE, SeasonKeys.SEASON_2016_2017);
		Map<Long, List<GameDto>> collect = todayGames.stream().collect(Collectors.groupingBy(GameDto::getRound));
		collect.forEach((r, q) -> {
				IntStream.range(0,q.size()).forEach(i->{
				    final GameDto gameDto = q.get(i);
					this.gameService.save(gameDto.getRound(), SeasonKeys.SEASON_2016_2017,
							LeagueKeys.SCOTTISH_PREMIER_LEAGUE, gameDto.getHomeTeam(), gameDto.getVisitorTeam(),
							gameDto.getHomeTeamGoals(), gameDto.getVisitorTeamGoals(), gameDto.getPlayAt(), (long) i + 1);
				});

		});
	}


}
