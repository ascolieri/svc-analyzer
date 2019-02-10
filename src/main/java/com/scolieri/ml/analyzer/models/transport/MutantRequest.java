package com.scolieri.ml.analyzer.models.transport;

import com.scolieri.ml.analyzer.validators.DnaSequence;

public class MutantRequest {

    @DnaSequence
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(final String[] dna) {
        this.dna = dna;
    }
}
