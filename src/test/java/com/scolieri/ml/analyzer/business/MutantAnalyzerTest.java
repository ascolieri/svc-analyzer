package com.scolieri.ml.analyzer.business;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantAnalyzerTest {

    @Autowired
    MutantAnalyzer mutantAnalyzer;

    @Test
    public void isMutantWithInsufficientGenes(){
        String[] dna = new String[]{"AA","BB"};
        boolean isMutant = mutantAnalyzer.isMutant(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantValidTest(){
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        boolean isMutant = mutantAnalyzer.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyDiagonalConsecutive(){
        String[] dna =  {"ATGCTA", "CAGTAC", "TTATTT", "AGAAGG", "CCTCTA", "TCACTG"};
        boolean isMutant = mutantAnalyzer.isMutant(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantOnlyColumnConsecutive(){
        String[] dna =  {"ATGCGA","CAGTGC","TTGTGT","AGAAGG","AACCTA","TCACTG"};
        boolean isMutant = mutantAnalyzer.isMutant(dna);
        Assert.assertFalse(isMutant);
    }

    @Test
    public void isMutantOnlyColumnConsecutiveValuesTest(){
        String[] dna = {"ATGCGA","ATGCGA","ATGCGA","ATGCGA"};
        boolean isMutant = mutantAnalyzer.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyRowConsecutiveValuesTest(){
        String[] dna = {"AAAAAA","TTTTTT","ATGCGA","ATGCGA"};
        boolean isMutant = mutantAnalyzer.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyDiagonalConsecutiveValuesTest(){
        String[] dna = {"ATGCGA","CAGTGC","TCATTT","AGCAGG","CTCCTA","TCACTG"};
        boolean isMutant = mutantAnalyzer.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void isMutantOnlyInverseDiagonalConsecutiveValuesTest(){
        String[] dna = {"TTGCGA","CTGTGC","TCCTAT","AGTAGG","CTACTA","TAACTG"};
        boolean isMutant = mutantAnalyzer.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

}
