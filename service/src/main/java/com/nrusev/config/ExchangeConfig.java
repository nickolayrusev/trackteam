package com.nrusev.config;

import com.jbetfairng.BetfairClient;
import com.jbetfairng.enums.Exchange;
import com.jbetfairng.exceptions.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Nikolay Rusev on 13.2.2017 г..
 */
@Configuration
@PropertySource({"betfair-exchange.properties"})
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

    @Autowired
    private CompetitionsConfig competitionsConfig;


    @Bean
    public BetfairClient betfairClient() throws LoginException {
        BetfairClient client = new BetfairClient(Exchange.UK, betfairAppKey);
        client.login(betfairCertificateLocation, betfairCertificatePassword, betfairUsername, betfairPassword);
        return client;
    }

    @Bean("competitions")
    public Set<CompetitionsConfig.Competition> getCompetitions()  {
        return competitionsConfig.getCompetitions().stream().collect(toSet());
    }

    @Bean("supportedCompetitions")
    public Set<CompetitionsConfig.Competition> getSupportedCompetitions(){
        return getCompetitions().stream().filter(s->s.isSupported()).collect(toSet());
    }
}
