package com.galaxy.domain;

import com.galaxy.domain.RomanNumber;
import com.galaxy.validation.Validator;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RomanNumberTest {

    @Test
    public void should_validate_roman_number_before_converting() {
        //Given
        RomanNumber romanNumber = new RomanNumber("I");
        Validator mockValidator = mock(Validator.class);
        romanNumber.setValidator(mockValidator);
        //When
        romanNumber.convert();
        //Then
        verify(mockValidator, times(1)).validate();
    }

    @Test
    public void should_convert_I_to_one() {
        int decimal = new RomanNumber("I").convert();
        assertThat(decimal, is(1));
    }

    @Test
    public void should_convert_X_to_ten() {
        int decimal = new RomanNumber("X").convert();
        assertThat(decimal, is(10));
    }

    @Test
    public void should_convert_L_to_fifty() {
        int decimal = new RomanNumber("L").convert();
        assertThat(decimal, is(50));
    }

    @Test
    public void should_convert_C_to_hundred() {
        int decimal = new RomanNumber("C").convert();
        assertThat(decimal, is(100));
    }

    @Test
    public void should_convert_D_to_five_hundred() {
        int decimal = new RomanNumber("D").convert();
        assertThat(decimal, is(500));
    }

    @Test
    public void should_convert_M_to_thousand() {
        int decimal = new RomanNumber("M").convert();
        assertThat(decimal, is(1000));
    }

    @Test
    public void should_convert_II_to_2() {
        int decimal = new RomanNumber("II").convert();
        assertThat(decimal, is(2));
    }

    @Test
    public void should_convert_IV_to_four() {
        int decimal = new RomanNumber("IV").convert();
        assertThat(decimal, is(4));
    }

    @Test
    public void should_convert_MCMXLIV_to_1944() {
        int decimal = new RomanNumber("MCMXLIV").convert();
        assertThat(decimal, is(1944));
    }
}