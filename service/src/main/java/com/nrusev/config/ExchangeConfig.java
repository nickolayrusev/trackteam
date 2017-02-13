package com.nrusev.config;

import com.jbetfairng.BetfairClient;
import com.jbetfairng.enums.Exchange;
import com.jbetfairng.exceptions.LoginException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

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

    @Value("${competitions}")
    private List<String> competitions;

    static class Competition{
        private Long id;
        private String region;

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
    }


    @Bean
    public BetfairClient betfairClient() throws LoginException {
        System.out.println(betfairCertificatePassword);
        BetfairClient client = new BetfairClient(Exchange.UK, betfairAppKey);
        client.login(betfairCertificateLocation, betfairCertificatePassword, betfairUsername, betfairPassword);
        return client;
    }

}
