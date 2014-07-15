package com.galaxy.validation;

public class Validator {

    private RepetitionValidator repetitionValidator;
    private SubtractionValidator subtractionValidator;

    public static Validator getInstance(String romanNumber) {
        Validator validator = new Validator();
        validator.repetitionValidator = new RepetitionValidator(romanNumber);
        validator.subtractionValidator = new SubtractionValidator(romanNumber);
        return validator;
    }


    public boolean validate() {
        return repetitionValidator.validate()
                && subtractionValidator.validate();
    }

    public void setRepetitionValidator(RepetitionValidator repetitionValidator) {
        this.repetitionValidator = repetitionValidator;
    }

    public void setSubtractionValidator(SubtractionValidator subtractionValidator) {
        this.subtractionValidator = subtractionValidator;
    }
}

