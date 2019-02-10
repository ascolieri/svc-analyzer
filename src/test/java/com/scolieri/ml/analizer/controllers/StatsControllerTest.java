package com.scolieri.ml.analizer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scolieri.ml.analizer.models.transport.StatsResponse;
import com.scolieri.ml.analizer.services.StatsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService statsService;

    private ObjectMapper mapper;

    @Before
    public void setUp(){
        mapper = new ObjectMapper();
    }

    @Test
    public void getStatsTest() throws Exception{
        StatsResponse statsResponse = new StatsResponse(1L,2L);
        String expectedJson = mapper.writeValueAsString(statsResponse);
        when(statsService.getStats()).thenReturn(statsResponse);
        mockMvc.perform(get("/stats/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedJson)));
    }
}
