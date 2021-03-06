package com.nrusev;

import com.nrusev.repository.SeasonRepository;
import com.nrusev.repository.TeamRepository;
import com.nrusev.service.TeamService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nikolayrusev on 2/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
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

}
