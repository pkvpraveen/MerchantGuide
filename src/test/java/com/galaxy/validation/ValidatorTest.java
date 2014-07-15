package com.galaxy.validation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ValidatorTest {

    private Validator validator;
    private RepetitionValidator mockRepetitionValidator;
    private SubtractionValidator mockSubtractionValidator;

    @Before
    public void setUp() throws Exception {
        validator = Validator.getInstance("XI");
        mockRepetitionValidator = mock(RepetitionValidator.class);
        mockSubtractionValidator = mock(SubtractionValidator.class);
        when(mockRepetitionValidator.validate()).thenReturn(true);
        when(mockSubtractionValidator.validate()).thenReturn(true);
        validator.setRepetitionValidator(mockRepetitionValidator);
        validator.setSubtractionValidator(mockSubtractionValidator);
    }

    @Test
    public void should_validate_repetition_and_subtraction() {

        //When
        validator.validate();
        //Then
        verify(mockRepetitionValidator, times(1)).validate();
        verify(mockSubtractionValidator, times(1)).validate();

    }
}