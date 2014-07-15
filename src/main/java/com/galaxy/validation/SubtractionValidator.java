package com.galaxy.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubtractionValidator extends Validator{
    protected String romanNumber;
    private Pattern invalidPatterns = Pattern.compile("I[L,C,D,M]|X[D,M]|V[X,L,C,D,M]|L[C,D,M]|D[M]");

    public SubtractionValidator(String romanNumber) {
        this.romanNumber = romanNumber;
    }

    @Override
    public boolean validate() {
        Matcher invalidMatcher = invalidPatterns.matcher(romanNumber);
        return !invalidMatcher.matches();
    }
}
