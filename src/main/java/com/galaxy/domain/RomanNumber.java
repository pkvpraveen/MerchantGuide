package com.galaxy.domain;

import com.galaxy.validation.Validator;

public class RomanNumber {

    private String romanNumber;
    private Validator validator;

    public RomanNumber(String romanNumber) {
        this.romanNumber = romanNumber;
        this.validator = Validator.getInstance(romanNumber);
    }

    public int convert() {
        validator.validate();
        return calculateDecimalValue(romanNumber);
    }


    private int calculateDecimalValue(String romanNumber) {
        int decimalValue = 0;
        for (char romanSymbol : reversed(romanNumber).toCharArray()) {
            decimalValue = updatedDecimalValue(decimalValue, romanSymbol);
        }
        return decimalValue;
    }

    private String reversed(String romanNumber) {
        return new StringBuffer(romanNumber).reverse().toString();
    }

    private int updatedDecimalValue(int decimal, char romanSymbol) {
        if (decimalValueOf(romanSymbol) < decimal)
            decimal -= decimalValueOf(romanSymbol);
        else
            decimal += decimalValueOf(romanSymbol);
        return decimal;
    }

    private int decimalValueOf(char romanSymbol) {
        return Roman.valueOf(String.valueOf(romanSymbol)).getValue();
    }


    enum Roman {
        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

        private int value;

        Roman(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
