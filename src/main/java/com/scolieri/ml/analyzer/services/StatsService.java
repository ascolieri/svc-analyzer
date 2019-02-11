package com.scolieri.ml.analyzer.services;

import com.scolieri.ml.analyzer.models.database.Stats;
import com.scolieri.ml.analyzer.models.transport.StatsResponse;
import com.scolieri.ml.analyzer.repositories.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that handle statistics about analyzed DNA
 */
@Service
public class StatsService {

    private StatsRepository statsRepository;

    @Autowired
    public StatsService(final StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    /**
     * @return a {@link StatsResponse} containing all the count of all the DNA already an what number of that corresponds to mutants.
     */
    public StatsResponse getStats(){
        Stats stats = statsRepository.findStats();
        return new StatsResponse(stats.getCountMutantDna(),stats.getCountHumanDna());
    }
}
