package com.scolieri.ml.analyzer.services;

import com.scolieri.ml.analyzer.models.database.Stats;
import com.scolieri.ml.analyzer.models.transport.StatsResponse;
import com.scolieri.ml.analyzer.repositories.StatsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsServiceTest {

    @Autowired
    private StatsService statsService;

    @MockBean
    private StatsRepository statsRepository;

    @Test
    public void getStatsTest(){
        Stats stats = new Stats(1L,2L);
        when(statsRepository.findStats()).thenReturn(stats);
        StatsResponse response = statsService.getStats();
        Assert.assertEquals(1L,response.getCountMutantDna().longValue());
        Assert.assertEquals(2L,response.getCountHumanDna().longValue());
    }
}
