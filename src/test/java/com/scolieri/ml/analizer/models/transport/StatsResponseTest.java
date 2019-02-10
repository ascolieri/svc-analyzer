package com.scolieri.ml.analizer.models.transport;

import org.junit.Assert;
import org.junit.Test;

public class StatsResponseTest {

    @Test
    public void responseTest(){
        StatsResponse statsResponse = new StatsResponse(1L,2L);
        Assert.assertEquals(1,statsResponse.getCountMutantDna().longValue());
        Assert.assertEquals(2,statsResponse.getCountHumanDna().longValue());
        Assert.assertEquals(0.5,statsResponse.getRatio(),0);
    }
}
