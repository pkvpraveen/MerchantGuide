package com.galaxy;

import com.galaxy.domain.Metal;
import com.galaxy.domain.RomanNumber;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MerchantGuide {

    private Map<String, String> symbolMap = new HashMap<String, String>();
    private Map<String, Metal> metalMap = new HashMap<String, Metal>();
    private List<String> answers = new ArrayList<String>();
    private final Pattern symbolAssignmentPattern = Pattern.compile("(.+)\\sis\\s(.)");
    private final Pattern pricePattern = Pattern.compile("((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$");
    private final Pattern howManyQuestionPattern = Pattern.compile("^how many Credits is ((?:[a-zA-Z]+ )+)\\?$");
    private final Pattern howMuchQuestionPattern = Pattern.compile("^how much is ((?:[a-z]+ )+)\\?$");

    public String processQuestionnaire(String questionnaire) {
        for (String line : getLinesFrom(questionnaire)) {
            if (storeIfSymbol(line) || storeIfPrice(line) || answerIfHowMany(line) || answerIfHowMuch(line)) ;
            else processUnknown();
        }
        return collectAndFormatAnswers().toString();

    }

    public boolean storeIfSymbol(String assignmentExpression) {
        Matcher matcher = symbolAssignmentPattern.matcher(assignmentExpression);
        boolean matchFound = matcher.matches();
        if (!matchFound) return false;
        symbolMap.put(matcher.group(1), matcher.group(2));
        return matchFound;
    }

    public boolean storeIfPrice(String priceExpression) {
        Matcher matcher = pricePattern.matcher(priceExpression);
        boolean matchFound = matcher.matches();
        if (!matchFound) return false;
        String nameOfMetal = matcher.group(2);
        Metal metal = Metal.detect(nameOfMetal);
        metal.setUnitPrice((float) Integer.valueOf(matcher.group(3)) / price(matcher));
        metalMap.put(nameOfMetal, metal);
        return matchFound;
    }

    private int price(Matcher matcher) {
        return new RomanNumber(romanEquivalentOf(matcher.group(1))).convert();
    }

    public boolean answerIfHowMany(String howManyExpression) {
        Matcher matcher = howManyQuestionPattern.matcher(howManyExpression);
        boolean matchFound = matcher.matches();
        if (!matchFound) return false;
        String[] split = matcher.group(1).split(" ");
        Metal metal = metalMap.get(split[split.length - 1]);

        int decimal = new RomanNumber(romanEquivalentOf(Arrays.copyOfRange(split, 0, split.length - 1))).convert();

        int total = Math.round(metal.getUnitPrice() * decimal);
        answers.add(matcher.group(1) + "is " + total + " Credits");

        return matchFound;
    }

    public boolean answerIfHowMuch(String howManyExpression) {
        Matcher matcher = howMuchQuestionPattern.matcher(howManyExpression);
        boolean matchFound = matcher.matches();
        if (matchNotFound(matchFound)) return false;
        int decimal = new RomanNumber(romanEquivalentOf(matcher.group(1))).convert();
        answers.add(matcher.group(1) + "is " + decimal);
        return matchFound;
    }

    private boolean matchNotFound(boolean matchFound) {
        return !matchFound;
    }

    public void processUnknown() {
        answers.add("I have no idea what you are talking about");
    }

    private String[] getLinesFrom(String questionnaire) {
        return questionnaire.split("\\n");
    }

    private String romanEquivalentOf(String galacticNumber) {
        String[] romanNumerals = galacticNumber.split(" ");
        return romanEquivalentOf(romanNumerals);
    }

    private String romanEquivalentOf(String[] romanNumerals) {
        StringBuffer romanNumber = new StringBuffer();
        for (String galacticNumeral : romanNumerals) {
            romanNumber.append(symbolMap.get(galacticNumeral));
        }
        return romanNumber.toString();
    }

    private StringBuffer collectAndFormatAnswers() {
        StringBuffer answerOut = new StringBuffer();
        for (String answer : answers) {
            answerOut.append(answer + "\n");
        }
        return answerOut;
    }

    public Map<String, Metal> getMetalMap() {
        return metalMap;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Map<String, String> getSymbolMap() {
        return symbolMap;
    }
}
