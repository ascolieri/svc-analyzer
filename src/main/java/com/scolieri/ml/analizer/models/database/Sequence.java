package com.scolieri.ml.analizer.models.database;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "dna")
public class Sequence {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "sequence")
    private String sequence;
    @Column(name = "is_mutant", columnDefinition="boolean")
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
