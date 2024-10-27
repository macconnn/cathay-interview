package com.cathayinterview.service.imp;

import com.cathayinterview.service.HttpService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpServiceImp implements HttpService {

    private final RestTemplate restTemplate;

    public HttpServiceImp(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getRequest(String url) {
        return this.restTemplate.getForObject(url, String.class);
    }
}
