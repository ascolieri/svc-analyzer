package com.scolieri.ml.analyzer.repositories;

import com.scolieri.ml.analyzer.models.database.Stats;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StatsRepository extends CrudRepository<Stats,Long> {

    @Modifying
    @Query("UPDATE stats SET count_mutant_dna = count_mutant_dna + 1, count_human_dna = count_mutant_dna + 1, mdt=CURRENT_TIMESTAMP")
    void updateMutantAndHumanCounter();

    @Modifying
    @Query("UPDATE stats SET count_human_dna = count_mutant_dna + 1, mdt=CURRENT_TIMESTAMP")
    void updateHumanCounter();

    @Query(value = "SELECT s.* from stats s limit 1",nativeQuery = true)
    Stats findStats();
}
