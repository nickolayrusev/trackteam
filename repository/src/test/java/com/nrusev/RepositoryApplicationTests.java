package com.nrusev;

import com.nrusev.domain.Team;
import com.nrusev.repository.TeamRepository;
import com.nrusev.repository.migration.MatchesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.ArrayUtils.nullToEmpty;

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
    public void testFindAllMatches() {
        matchesRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testFindAllMatchesForCountry() {
        List<String> italy = matchesRepository.findAll("italy").stream().map(t -> t.getName1()).distinct().sorted().collect(toList());
        List<Team> italyOriginalDatabase = teamRepository.findByCountryName("Italy");
        System.out.println(italy.size());
        System.out.println(italyOriginalDatabase.size());

        Map<Team, String> collect = italyOriginalDatabase.stream().collect(toMap(Function.identity(), team -> {
            if (team.getSynonyms() == null) return "";
            else return team.getSynonyms();
        }));


        italy.forEach(t -> {
            List<Team> byTitleIgnoreCase = teamRepository.findByTitleIgnoreCase(t);
            if (byTitleIgnoreCase.size() == 0) {
                italyOriginalDatabase.forEach(team -> {
                    String synonyms = team.getSynonyms();
                    if (team.getSynonyms() != null) {
                        String[] strings = synonyms.split("\\|");
                        if (strings.length > 0) {
                            System.out.println(t + " synonyms are " + Arrays.toString(strings));
                        }
                    }
                });
                System.out.println("team " + t + " is not found");
            }
//			if(byTitleIgnoreCase.size()==1)
//				System.out.println("team "+t + " is found exactly the same");
            if (byTitleIgnoreCase.size() > 1)
                System.out.println("team " + t + " is found more than once");
        });
    }


}
