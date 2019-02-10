package com.scolieri.ml.analizer.models.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "dna")
public class Sequence {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name = "sequence")
    private String sequence;
    @Column(name = "isMutant", columnDefinition="boolean")
    private Boolean isMutant;


    public String getSequence() {
        return sequence;
    }

    public Boolean isMutant() {
        return isMutant;
    }

    public Sequence(final String sequence, final  Boolean isMutant) {
        this.sequence = sequence;
        this.isMutant = isMutant;
    }

    public Sequence(){

    }
}
