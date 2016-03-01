package com.nrusev.config;

import com.nrusev.support.HeaderRequestInterceptor;
import com.nrusev.support.UrlRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolayrusev on 2/22/16.
 */
@Configuration
public class RootConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "footballApiTemplate")
    public RestTemplate footballApiTemplate(){
        RestTemplate template = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new HeaderRequestInterceptor("X-Auth-Token", environment.getProperty("football.data.api.token")));
        interceptors.add(new HeaderRequestInterceptor("X-Response-Control", environment.getProperty("football.data.api.token")));
//        X-Response-Control
        interceptors.add(new UrlRequestInterceptor(environment.getProperty("football.data.url")));
        template.setInterceptors(interceptors);
        return template;
    }
}
