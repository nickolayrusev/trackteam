package com.nrusev.config;

import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Nikolay Rusev on 16.2.2017 г..
 */
@Configuration
@PropertySource("xml-soccer-exchange.properties")
public class XmlSoccerConfig {
    @Value("${xml-soccer.apiKey}")
    private String apiKey;

    @Bean
    public XmlSoccerService client() {

        XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey(apiKey);

        // demo access
        xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");

        // full access
//        xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");
        return xmlSoccerService;
    }
}
