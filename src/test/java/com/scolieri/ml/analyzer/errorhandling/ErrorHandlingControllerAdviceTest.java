package com.scolieri.ml.analyzer.errorhandling;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorHandlingControllerAdviceTest {

    @Autowired
    ErrorHandlingControllerAdvice controllerAdvice;

    @Test
    public void onConstraintValidationExceptionTest(){
        ConstraintViolation violation = mock(ConstraintViolation.class);
        when(violation.getPropertyPath()).thenReturn(mock(Path.class));
        when(violation.getMessage()).thenReturn("MESSAGE");
        Set<ConstraintViolation<?>> violations = new HashSet<>();
        violations.add(violation);
        ConstraintViolationException exception = new ConstraintViolationException("MESSAGE",violations);

        ValidationErrorResponse response = controllerAdvice.onConstraintValidationException(exception);
        Assert.assertNotNull(response.getViolations().get(0).getFieldName());
        Assert.assertEquals("MESSAGE",response.getViolations().get(0).getMessage());

    }

    @Test
    public void onMethodArgumentNotValidExceptionTest(){

        MethodParameter methodParameter = mock(MethodParameter.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getField()).thenReturn("FIELD");
        when(fieldError.getDefaultMessage()).thenReturn("DEFAULT_MESSAGE");
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        MethodArgumentNotValidException exception  = new MethodArgumentNotValidException(methodParameter,bindingResult);

        ValidationErrorResponse response = controllerAdvice.onMethodArgumentNotValidException(exception);
        Assert.assertEquals("FIELD",response.getViolations().get(0).getFieldName());
        Assert.assertEquals("DEFAULT_MESSAGE",response.getViolations().get(0).getMessage());

    }
}
