package com.scolieri.ml.analizer.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantServiceTest {

    @Autowired
    private MutantService mutantService;

    @Test
    public void isMutantWithInsufficientGenes(){
        String[] dna = new String[]{"AA","BB"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantValidTest(){
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyDiagonalConsecutive(){
        String[] dna =  {"ATGCTA", "CAGTAC", "TTATTT", "AGAAGG", "CCTCTA", "TCACTG"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantOnlyColumnConsecutive(){
        String[] dna =  {"ATGCGA","CAGTGC","TTGTGT","AGAAGG","AACCTA","TCACTG"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantOnlyRowConsecutive(){
        String[] dna =  {"TTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "ACCCTA", "TCACTG"};

    }

    @Test
    public void isMutantOnlyColumnConsecutiveValuesTest(){
        String[] dna = {"ATGCGA","ATGCGA","ATGCGA","ATGCGA"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyRowConsecutiveValuesTest(){
        String[] dna = {"AAAAAA","TTTTTT","ATGCGA","ATGCGA"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyDiagonalConsecutiveValuesTest(){
        String[] dna = {"ATGCGA","CAGTGC","TCATTT","AGCAGG","CTCCTA","TCACTG"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

}
