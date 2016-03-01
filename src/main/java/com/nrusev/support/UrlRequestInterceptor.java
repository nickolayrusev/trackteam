package com.nrusev.support;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriUtils;

import java.io.IOException;

/**
 * Created by nikolayrusev on 2/22/16.
 */
public class UrlRequestInterceptor implements ClientHttpRequestInterceptor {
    private String baseUrl;

    /**
     *
     * @param baseUrl
     */
    public UrlRequestInterceptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     *
     * @param request
     * @param body
     * @param execution
     * @return
     * @throws
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if(request.getURI().isAbsolute())
            return execution.execute(request,body);
        HttpRequest wrapper = new HttpRequestDecorator(request, this.baseUrl);
        return execution.execute(wrapper, body);
    }

}
