package com.scolieri.ml.analizer.services;

import com.scolieri.ml.analizer.models.database.Sequence;
import com.scolieri.ml.analizer.repositories.SequenceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SequenceServiceTest {

    @Autowired
    private SequenceService sequenceService;

    @MockBean
    private SequenceRepository sequenceRepository;

    @Test
    public void isMutantWithInsufficientGenes(){
        String[] dna = new String[]{"AA","BB"};
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantValidTest(){
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.empty());
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyDiagonalConsecutive(){
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.empty());
        String[] dna =  {"ATGCTA", "CAGTAC", "TTATTT", "AGAAGG", "CCTCTA", "TCACTG"};
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantOnlyColumnConsecutive(){
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.empty());
        String[] dna =  {"ATGCGA","CAGTGC","TTGTGT","AGAAGG","AACCTA","TCACTG"};
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantOnlyColumnConsecutiveValuesTest(){
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.empty());
        String[] dna = {"ATGCGA","ATGCGA","ATGCGA","ATGCGA"};
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyRowConsecutiveValuesTest(){
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.empty());
        String[] dna = {"AAAAAA","TTTTTT","ATGCGA","ATGCGA"};
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyDiagonalConsecutiveValuesTest(){
        when(sequenceRepository.findBySequence(any())).thenReturn(Optional.empty());
        String[] dna = {"ATGCGA","CAGTGC","TCATTT","AGCAGG","CTCCTA","TCACTG"};
        boolean isMutant = sequenceService.validateSequence(dna);
        Assert.assertTrue(isMutant);
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
