package com.scolieri.ml.analyzer.errorhandling;

public class Violation {
    private final String fieldName;

    private final String message;

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    public Violation(final String fieldName, final String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
