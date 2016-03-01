package com.nrusev;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrusev.domain.Season;
import com.nrusev.domain.Team;
import com.nrusev.repository.SeasonRepository;
import com.nrusev.repository.TeamRepository;
import com.nrusev.service.TeamService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by nikolayrusev on 2/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EasyApplication.class)
@WebAppConfiguration
public class FootballData {
    @Autowired
    private RestTemplate footballDataApiTemplate;

    private static final String BASE_PATH = "/Users/nikolayrusev/Downloads/footballapidata/";

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @Before
    public void init(){
        Assert.assertNotNull(footballDataApiTemplate);
    };

    @Test
    public void suckSeasons(){
        ResponseEntity<String> test = footballDataApiTemplate.getForEntity("soccerseasons", String.class);
        System.out.println(test.getHeaders());
//        System.out.println(test.getBody());

    }

    @Test
    public void writeSoccerSeasons() throws IOException {
        ResponseEntity<String> test = footballDataApiTemplate.getForEntity("soccerseasons", String.class);
        Files.write(Paths.get(BASE_PATH + "seasons.json"),test.getBody().getBytes());
    }

    @Test
    public void writeSeasonsInDatabase() throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(new File(BASE_PATH + "seasons.json"));
        List<Season> seasons = new ObjectMapper().readValue(new File(BASE_PATH+"seasons.json"),new TypeReference<List<Season>>(){});
        seasons.forEach(season -> {
            seasonRepository.save(season);
        });

    }

    @Test
    public void writeBundesligaTeams() throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(new File(BASE_PATH + "seasons.json"));
        jsonNode.elements().forEachRemaining(node->{
            Long seasonId = node.get("id").asLong();
            if(seasonId.equals(425L)){
                System.out.println("\nseason is is "+seasonId);
                String teamsUrl = node.get("_links").get("teams").get("href").asText();
                System.out.println("teams url is "+teamsUrl);

                ResponseEntity<String> test = footballDataApiTemplate.getForEntity(teamsUrl, String.class);
                System.out.println(test.getBody());
                try {
                    JsonNode jsonNodeTeamsWrapper = new ObjectMapper().readTree(test.getBody());
                    JsonNode teamsAsText = jsonNodeTeamsWrapper.get("teams");

                    teamsAsText.elements().forEachRemaining(team->{
                        System.out.println("teams as text " + teamsAsText);
                        String name = team.get("name").asText();
                        String code = team.get("code").asText();
                        String shortName = team.get("shortName").asText();
                        String crestUrl = team.get("crestUrl").asText();
                        String teamUrl = team.get("_links").get("self").get("href").asText();
                        int i = teamUrl.lastIndexOf("/")+1;
                        Long teamId = Long.valueOf(teamUrl.substring(i));

                        Team dbTeam = new Team();
                        dbTeam.setId(teamId);
                        dbTeam.setName(name);
                        dbTeam.setCode(code);
                        dbTeam.setCrestUrl(crestUrl);
                        dbTeam.setShortName(shortName);
                        teamRepository.save(dbTeam);

                        teamService.addTeamToSeason(dbTeam.getId(),seasonId);
                    });

                } catch (IOException e) {
                    System.err.println("error: "+e.getMessage());
                }


            }

        });
//        System.out.println(jsonNode);
    }
}
