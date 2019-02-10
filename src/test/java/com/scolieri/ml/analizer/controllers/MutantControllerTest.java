package com.scolieri.ml.analizer.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.scolieri.ml.analizer.models.transport.MutantRequest;
import com.scolieri.ml.analizer.services.SequenceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SequenceService sequenceService;

    private ObjectMapper mapper;

    @Before
    public void setUp(){
       mapper = new ObjectMapper();
    }

    @Test
    public void isMutantTrueTest() throws Exception {
        String[] dna =new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        when(sequenceService.validateSequence(dna)).thenReturn(true);
        MutantRequest mutantRequest = new MutantRequest();
        mutantRequest.setDna(dna);
        String body = this.mapper.writeValueAsString(mutantRequest);
        this.mockMvc.perform(post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void isMutantBadRequestNotSquareMatrixTest() throws Exception{
        String[] dna =new String[]{"ATG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        MutantRequest mutantRequest = new MutantRequest();
        mutantRequest.setDna(dna);
        String body = this.mapper.writeValueAsString(mutantRequest);
        this.mockMvc.perform(post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isMutantBadRequestEmptyDnaFieldTest() throws Exception{
        String[] dna =new String[]{};
        MutantRequest mutantRequest = new MutantRequest();
        mutantRequest.setDna(dna);
        String body = this.mapper.writeValueAsString(mutantRequest);
        this.mockMvc.perform(post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isMutantBadRequestNullDnaFieldTest() throws Exception{
        MutantRequest mutantRequest = new MutantRequest();
        String body = this.mapper.writeValueAsString(mutantRequest);
        this.mockMvc.perform(post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isMutantFalseTest() throws Exception{
        String[] dna =new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        when(sequenceService.validateSequence(dna)).thenReturn(false);
        MutantRequest mutantRequest = new MutantRequest();
        mutantRequest.setDna(dna);
        String body = this.mapper.writeValueAsString(mutantRequest);
        this.mockMvc.perform(post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body))
                .andExpect(status().isForbidden());
    }
}
