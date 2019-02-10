package com.scolieri.ml.analyzer.services;

import com.scolieri.ml.analyzer.models.database.Stats;
import com.scolieri.ml.analyzer.models.transport.StatsResponse;
import com.scolieri.ml.analyzer.repositories.StatsRepository;
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
