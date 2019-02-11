package com.scolieri.ml.analyzer.errorhandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a validation error
 */
public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();

    public List<Violation> getViolations() {
        return violations;
    }
}
