package com.scolieri.ml.analyzer.models.transport;

import com.scolieri.ml.analyzer.validators.DnaSequence;
import io.swagger.annotations.ApiModelProperty;

public class MutantRequest {

    @ApiModelProperty(notes = "An array of DNA chains")
    @DnaSequence
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(final String[] dna) {
        this.dna = dna;
    }
}
