package com.nrusev.config;

import com.jbetfairng.BetfairClient;
import com.jbetfairng.enums.Exchange;
import com.jbetfairng.exceptions.LoginException;
import com.nrusev.domain.User;
import com.nrusev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Optional;

/**
 * Created by Nikolay Rusev on 17.1.2017 г..
 */
@Configuration
@PropertySource("service-application.properties")
public class ServiceConfig {
    private final UserService userService;

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
    public ServiceConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public User user(){
        return userService.findByUserName("nrusev").orElseThrow(IllegalStateException::new);
    }

    @Bean
    public BetfairClient betfairClient() throws LoginException {
        System.out.println(betfairCertificatePassword);
        BetfairClient client = new BetfairClient(Exchange.UK, betfairAppKey);
        client.login(betfairCertificateLocation, betfairCertificatePassword, betfairUsername, betfairPassword);
        return client;
    }
}
