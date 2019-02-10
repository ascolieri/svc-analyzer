package com.scolieri.ml.analizer.models.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "stats")
public class Stats {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(name ="count_mutant_dna")
    private Long countMutantDna;
    @Column(name = "count_human_dna")
    private Long countHumanDna;

    public Long getCountMutantDna() {
        return countMutantDna;
    }

    public Long getCountHumanDna() {
        return countHumanDna;
    }

    public Stats(final Long countMutantDna, final Long countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
    }

    public Stats(){

    }
}
