package com.nrusev.config;

import com.nrusev.exchange.impl.Competition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by Nikolay Rusev on 17.2.2017 г..
 */

@Configuration
@ConfigurationProperties(locations = "classpath:xml-soccer-competitions.yml")
public class XmlSoccerCompetitionsConfig {

    private List<Competition> competitions;

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

}
