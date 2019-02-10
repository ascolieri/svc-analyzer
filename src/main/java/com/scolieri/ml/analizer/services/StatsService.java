package com.scolieri.ml.analizer.services;

import com.scolieri.ml.analizer.models.database.Stats;
import com.scolieri.ml.analizer.models.transport.StatsResponse;
import com.scolieri.ml.analizer.repositories.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private StatsRepository statsRepository;

    @Autowired
    public StatsService(final StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public StatsResponse getStats(){
        Stats stats = statsRepository.findStats();
        return new StatsResponse(stats.getCountMutantDna(),stats.getCountHumanDna());
    }
}
