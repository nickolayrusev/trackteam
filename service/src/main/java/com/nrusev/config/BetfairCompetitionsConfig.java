package com.nrusev.config;

import com.nrusev.exchange.impl.Competition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by nikolayrusev on 2/15/17.
 */


@Configuration
@ConfigurationProperties(locations = "classpath:betfair-competitions.yml")
public class BetfairCompetitionsConfig {

    private List<Competition> competitions;

    public BetfairCompetitionsConfig() {
    }

    public BetfairCompetitionsConfig(List<Competition> competitions) {
        this.competitions = competitions;
    }


    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
