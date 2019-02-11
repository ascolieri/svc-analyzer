package com.scolieri.ml.analyzer.services;

import com.google.common.hash.Hashing;
import com.scolieri.ml.analyzer.business.MutantAnalyzer;
import com.scolieri.ml.analyzer.models.database.Sequence;
import com.scolieri.ml.analyzer.repositories.SequenceRepository;
import com.scolieri.ml.analyzer.repositories.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Service that handle DNA sequence related logic
 */
@Service
@Transactional
public class SequenceService {

    private SequenceRepository sequenceRepository;
    private StatsRepository statsRepository;
    private MutantAnalyzer mutantAnalyzer;

    @Autowired
    public SequenceService(final SequenceRepository sequenceRepository,
                           final StatsRepository statsRepository,
                           final MutantAnalyzer mutantAnalyzer) {
        this.sequenceRepository = sequenceRepository;
        this.statsRepository = statsRepository;
        this.mutantAnalyzer = mutantAnalyzer;
    }

    /**
     * Analyze the DNA sequence if it is not already analyzed and in the database.
     * If it need to analyze it it will store the result in the database after doing it.
     * @param dna a DNA string
     * @return true if the subject is mutant or false if it is human.
     */
    public Boolean validateSequence(String[] dna){
        Boolean mutantResult;
        String dnaHash = getHash(dna);
        Optional<Sequence> existingSequence = sequenceRepository.findBySequence(dnaHash);

        if(existingSequence.isPresent()){
            return existingSequence.get().isMutant();
        }
        mutantResult = mutantAnalyzer.isMutant(dna);
        onNewDnaRecord(dnaHash,mutantResult);
        return mutantResult;
    }

    private void onNewDnaRecord(String dnaHash,Boolean isMutant){
        updateCounters(isMutant);
        Sequence newSequence = new Sequence(dnaHash,isMutant);
        sequenceRepository.save(newSequence);
    }

    private void updateCounters(Boolean isMutant){
        if(isMutant){
            statsRepository.updateMutantAndHumanCounter();
        }else{
            statsRepository.updateHumanCounter();
        }
    }

    private String getHash(String[] dna){
        return Hashing.sha512().hashString(String.join("",dna),StandardCharsets.UTF_8).toString();
    }


}
