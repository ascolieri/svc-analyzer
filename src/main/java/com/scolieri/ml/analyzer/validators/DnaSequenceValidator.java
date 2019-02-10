package com.scolieri.ml.analyzer.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DnaSequenceValidator implements ConstraintValidator<DnaSequence,String[]> {

    @Override
    public boolean isValid(final String[] value, final ConstraintValidatorContext context) {
        if (value!= null && value.length > 0 && value.length == value[0].length()){
         for(int i = 1; i < value.length; i++){
             if(value[i].length() != value[0].length()) return false;
         }
         return true;
        }
        return false;
    }
}
