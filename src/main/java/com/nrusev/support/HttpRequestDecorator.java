package com.nrusev.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;

/**
 * Created by nikolayrusev on 2/22/16.
 */
public class HttpRequestDecorator extends HttpRequestWrapper {

    private HttpHeaders httpHeaders;

    private boolean existingHeadersAdded;

    private String baseUrl;

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestDecorator.class);

    public HttpRequestDecorator(HttpRequest request,String baseUrl) {
        super(request);
        this.baseUrl = baseUrl;
    }

    public HttpHeaders getHeaders() {
        if (!existingHeadersAdded) {
            this.httpHeaders = new HttpHeaders();
            httpHeaders.putAll(getRequest().getHeaders());
            existingHeadersAdded = true;
        }
        return httpHeaders;
    }

    @Override
    public URI getURI() {
        URI url = null;
        try {
            //TODO: report bug in spring ! ref: http://stackoverflow.com/questions/29094431/url-encoding-using-springs-resttemplate-uricomponentsbuilder-with-a-file-uri
            url = UriComponentsBuilder.fromUriString(this.baseUrl + "/" + UriUtils.decode(super.getURI().toString(), "UTF-8")).build().toUri();
        } catch (UnsupportedEncodingException e) {
            logger.error("unsupported encoding ",e);
        }

        return url;
    }

}
