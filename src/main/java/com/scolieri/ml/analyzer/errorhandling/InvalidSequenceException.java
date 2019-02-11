package com.scolieri.ml.analyzer.errorhandling;

public class InvalidSequenceException extends RuntimeException {

    public String getFieldName(){
        return "dna";
    }

    public InvalidSequenceException() {
        super("DNA sequence can only contains letters A,T,C,G");
    }
}
