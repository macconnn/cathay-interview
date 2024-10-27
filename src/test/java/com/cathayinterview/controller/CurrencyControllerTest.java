package com.cathayinterview.controller;


import com.cathayinterview.CathayInterviewApplication;
import com.cathayinterview.entity.CurrencyEntity;
import com.cathayinterview.service.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.context.WebApplicationContext;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CathayInterviewApplication.class)
@WebAppConfiguration
public class CurrencyControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private CurrencyService currencyService;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void Test_getCurrencyByCode() throws Exception {
         CurrencyEntity currencyEntity = currencyService.findAllByCode("USD");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/currency/USD")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(currencyEntity.toString()));
    }

    @Test
    public void Test_addNewCurrency() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        CurrencyEntity newCurrencyEntity = new CurrencyEntity();
        newCurrencyEntity.setCode("TST");
        newCurrencyEntity.setSymbol("test");
        newCurrencyEntity.setRate("123456");
        newCurrencyEntity.setDescription("this is an add test");
        newCurrencyEntity.setRate_float(123456);
        String jsonCurrency = objectMapper.writeValueAsString(newCurrencyEntity);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/currency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCurrency)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        CurrencyEntity currencyEntity = currencyService.findAllByCode("TST");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/currency/TST")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(currencyEntity.toString()));
    }

    @Test
    public void Test_updateCurrencyByCode() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        CurrencyEntity updateCurrencyEntity = new CurrencyEntity();
        updateCurrencyEntity.setCode("USD");
        updateCurrencyEntity.setSymbol("test update");
        updateCurrencyEntity.setRate("123456");
        updateCurrencyEntity.setDescription("this is an update test");
        updateCurrencyEntity.setRate_float(123456);
        String jsonCurrency = objectMapper.writeValueAsString(updateCurrencyEntity);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/currency/USD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCurrency)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        CurrencyEntity currencyEntity = currencyService.findAllByCode("USD");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/currency/USD")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(currencyEntity.toString()));
    }

    @Test
    public void Test_deleteCurrencyByCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/currency/USD")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/currency/USD")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("currency not found"));
    }

}
