package com.scolieri.ml.analizer.validators;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = DnaSequenceValidator.class)
@Documented
public @interface DnaSequence {

    String message() default "Dna Secuence must be an NxN matrix";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
