package com.scolieri.ml.analyzer.models.database;

import org.junit.Assert;
import org.junit.Test;


public class SequenceTest {
    @Test
    public void constructorTest(){
        Sequence sequence = new Sequence();
        Assert.assertNotNull(sequence);
    }

    @Test
    public void constructorParamTest(){
        Sequence sequence = new Sequence("SEQUENCE",true);
        Assert.assertTrue(sequence.isMutant());
        Assert.assertEquals("SEQUENCE",sequence.getSequence());
    }
}
