package com.galaxy.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepetitionValidator extends Validator {


    private final Pattern invalidPattern = Pattern.compile("XXXX+|IIII+|CCCC+|MMMM+");
    private String romanNumber;

    public RepetitionValidator(String romanNumber) {
        this.romanNumber = romanNumber;
    }

    @Override
    public boolean validate() {
        return noInvalidPattern() && noRepetitionOf("D", "L","V");
    }

    private boolean noInvalidPattern() {
        Matcher invalidPatternMatcher = invalidPattern.matcher(romanNumber);
        return !invalidPatternMatcher.matches();
    }

    private boolean noRepetitionOf(String... numerals) {
        for (String numeral : numerals) {
            if (StringUtils.countMatches(romanNumber, numeral) > 1)
                return false;
        }
        return true;
    }


}
