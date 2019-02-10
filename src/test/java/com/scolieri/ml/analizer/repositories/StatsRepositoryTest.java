package com.scolieri.ml.analizer.repositories;

import com.scolieri.ml.analizer.models.database.Stats;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatsRepositoryTest {

    @Autowired
    private StatsRepository statsRepository;

    @Test
    public void updateMutantAndHumanCounter(){
        statsRepository.updateMutantAndHumanCounter();
        Stats statsList = statsRepository.findStats();
        Assert.assertEquals(1,statsList.getCountHumanDna().longValue());
        Assert.assertEquals(1,statsList.getCountMutantDna().longValue());
    }

    @Test
    public void updateHumanCounter(){
        statsRepository.updateHumanCounter();
        Stats statsList = statsRepository.findStats();
        Assert.assertEquals(1,statsList.getCountHumanDna().longValue());
        Assert.assertEquals(0,statsList.getCountMutantDna().longValue());
    }
}
