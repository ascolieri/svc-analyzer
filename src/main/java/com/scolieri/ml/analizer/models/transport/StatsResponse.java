package com.scolieri.ml.analizer.models.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsResponse {
    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;
    @JsonProperty("count_human_dna")
    private Long countHumanDna;

    public Long getCountMutantDna() {
        return countMutantDna;
    }

    public Long getCountHumanDna() {
        return countHumanDna;
    }

    @JsonProperty("ratio")
    public Double getRatio(){
        return this.countMutantDna.doubleValue() / this.countHumanDna.doubleValue();
    }

    public StatsResponse(final Long countMutantDna, final Long countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
    }
}
