package com.scolieri.ml.analyzer.models.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class StatsResponse {
    @ApiModelProperty(notes = "Number of mutant DNA processed at the moment")
    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;
    @ApiModelProperty(notes = "Total number of DNA processed")
    @JsonProperty("count_human_dna")
    private Long countHumanDna;

    public Long getCountMutantDna() {
        return countMutantDna;
    }

    public Long getCountHumanDna() {
        return countHumanDna;
    }

    @JsonProperty("ratio")
    @ApiModelProperty(notes = "Ratio of mutant DNA vs human DNA")
    public Double getRatio(){
        return this.countMutantDna.doubleValue() / this.countHumanDna.doubleValue();
    }

    public StatsResponse(final Long countMutantDna, final Long countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
    }
}
