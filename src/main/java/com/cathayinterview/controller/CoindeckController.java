package com.cathayinterview.controller;

import com.cathayinterview.service.HttpService;
import com.cathayinterview.utilities.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coindeck")
public class CoindeckController {

    private final String COINDECK_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Autowired
    HttpService httpService;
    @Autowired
    JsonParser jsonParser;

    @RequestMapping(value = "/original", method = RequestMethod.GET)
    public String getOriginalCoindeckInfo(){
        String response = httpService.getRequest(COINDECK_URL);
        if(response.isEmpty() || response == null){
            return "the coindeck url failed";
        }

        return response;
    }

    @RequestMapping(value = "modified", method = RequestMethod.GET)
    public String getNewCoindeckInfo(){
        String original = httpService.getRequest(COINDECK_URL);
        if(original.isEmpty() || original == null){
            return "the coindeck url failed";
        }

        String response = jsonParser.coindeckParser(original);
        if(response == null){
            return "json parser error";
        }

        return response;
    }


}
