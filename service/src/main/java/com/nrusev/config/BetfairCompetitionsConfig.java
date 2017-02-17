package com.nrusev.config;

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

    public static class Competition{
        private Long id;
        private String region;
        private String name;
        private boolean supported;

        public Competition(){

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSupported() {
            return supported;
        }

        public void setSupported(boolean supported) {
            this.supported = supported;
        }
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
