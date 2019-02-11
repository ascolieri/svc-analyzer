package com.scolieri.ml.analyzer.services;

import com.scolieri.ml.analyzer.business.MutantAnalyzer;
import com.scolieri.ml.analyzer.models.database.Sequence;
import com.scolieri.ml.analyzer.repositories.SequenceRepository;
import com.scolieri.ml.analyzer.repositories.StatsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SequenceServiceTest {

    @Autowired
    private SequenceService sequenceService;

    @MockBean
    private SequenceRepository sequenceRepository;

    @MockBean
    private StatsRepository statsRepository;

    @MockBean
    private MutantAnalyzer mutantAnalyzer;

    @Test
    public void isMutantValueNotInDbAndIsMutantTest(){
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.empty());
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        when(mutantAnalyzer.isMutant(dna)).thenReturn(true);
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertTrue(isMutant);
        verify(statsRepository,times(1)).updateMutantAndHumanCounter();
        verify(sequenceRepository,times(1)).save(any());
    }

    @Test
    public void isMutantValueNotInDbAndNotMutantTest(){
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.empty());
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        when(mutantAnalyzer.isMutant(dna)).thenReturn(false);
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertFalse(isMutant);
        verify(statsRepository,times(1)).updateHumanCounter();
        verify(sequenceRepository,times(1)).save(any());
    }

    @Test
    public void isMutantValueAlreadyOnDbTest(){
        Sequence sequence = new Sequence("SEQUENCE",true);
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.of(sequence));
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertTrue(isMutant);
    }
}
