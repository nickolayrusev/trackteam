package com.nrusev.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jbetfairng.BetfairClient;
import com.jbetfairng.enums.Exchange;
import com.jbetfairng.exceptions.LoginException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Nikolay Rusev on 13.2.2017 г..
 */
@Configuration
@PropertySource({"betfair-exchange.properties","betfair-competitions-config.yml"})
public class ExchangeConfig {

    @Value("${betfair.appKey}")
    private String betfairAppKey;

    @Value("${betfair.username}")
    private String betfairUsername;

    @Value("${betfair.password}")
    private String betfairPassword;

    @Value("${BETFAIR_CERTIFICATE_PASSWORD}")
    private String betfairCertificatePassword;

    @Value("${BETFAIR_CERTIFICATE_LOCATION}")
    private String betfairCertificateLocation;


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


    @Bean
    public BetfairClient betfairClient() throws LoginException {
        BetfairClient client = new BetfairClient(Exchange.UK, betfairAppKey);
        client.login(betfairCertificateLocation, betfairCertificatePassword, betfairUsername, betfairPassword);
        return client;
    }

    @Bean("competitions")
    public Set<Competition> getCompetitions()  {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Set<Competition> value = null;
        try {
            value = mapper.readValue(getClass().getClassLoader().getResourceAsStream("betfair-competitions-config.yml"), new TypeReference<Set<Competition>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Bean("supportedCompetitions")
    public Set<Competition> getSupportedCompetitions(){
        return getCompetitions().stream().filter(s->s.supported).collect(toSet());
    }
}
