package com.cathayinterview.utilities;

import com.cathayinterview.model.Price;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpUtility {
    private final RestTemplate restTemplate;

    public HttpUtility(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String postCathayApi(){
        String url = "https://www.cathaybk.com.tw/cathaybk/service/newwealth/fund/chartservice.asmx/GetFundNavChart";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> req = new HashMap<>();
        req.put("Keys", new String[]{"10480016"});
        req.put("From", "2023/03/10");
        req.put("To", "2024/03/10");

        Map<String, Object> payload = new HashMap<>();
        payload.put("req", req);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
}
