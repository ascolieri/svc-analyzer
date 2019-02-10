package com.scolieri.ml.analizer.models;

import com.scolieri.ml.analizer.validators.DnaSequence;

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
