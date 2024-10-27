package com.cathayinterview.controller;

import com.cathayinterview.CathayInterviewApplication;
import com.cathayinterview.service.HttpService;
import com.cathayinterview.utilities.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CathayInterviewApplication.class)
@WebAppConfiguration
public class CoindeckControllerTest {

    private final String COINDECK_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private HttpService httpService;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void Test_getOriginalCoindeckInfo() throws Exception {
        String originalResponse = httpService.getRequest(COINDECK_URL);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/coindeck/original")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(originalResponse));
    }

    @Test
    public void Test_getNewCoindeckInfo() throws Exception {
        JsonParser jsonParser = new JsonParser();
        String originalResponse = httpService.getRequest(COINDECK_URL);
        String testNewCoindeckInfo = jsonParser.coindeckParser(originalResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/coindeck/modified")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(testNewCoindeckInfo));
    }


}
