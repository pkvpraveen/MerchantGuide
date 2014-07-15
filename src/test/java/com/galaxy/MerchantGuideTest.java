package com.galaxy;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.IsCollectionContaining.hasItem;

public class MerchantGuideTest {

    private MerchantGuide merchantGuide;

    @Before
    public void setUp() throws Exception {
        merchantGuide = new MerchantGuide();
    }

    @Test
    public void should_process_questionnaire(){
        String questionnaire = "glob is I\n" +
                "prok is V\n" +
                "pish is X\n" +
                "tegj is L\n" +
                "glob glob Silver is 34 Credits\n" +
                "glob prok Gold is 57800 Credits\n" +
                "pish pish Iron is 3910 Credits\n" +
                "how much is pish tegj glob glob ?\n" +
                "how many Credits is glob prok Silver ?\n" +
                "how many Credits is glob prok Gold ?\n" +
                "how many Credits is glob prok Iron ?\n" +
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
        //When
        String answer = merchantGuide.processQuestionnaire(questionnaire);
        //Then
        assertThat(answer,is("pish tegj glob glob is 42\n" +
                "glob prok Silver is 68 Credits\n" +
                "glob prok Gold is 57800 Credits\n" +
                "glob prok Iron is 782 Credits\n" +
                "I have no idea what you are talking about\n"));
    }


    @Test
    public void should_store_assigned_galactic_symbols_against_roman_symbols() {

        //When
        boolean matchFound = merchantGuide.storeIfSymbol("glob is I");
        //Then
        assertThat(matchFound, is(true));
        merchantGuide.getSymbolMap().containsKey("glob");
        assertThat(merchantGuide.getSymbolMap().get("glob"), is("I"));
    }

    @Test
    public void return_false_if_no_match_found() {
        //When
        boolean matchFound = merchantGuide.storeIfSymbol("Its my birthday");
        //Then
        assertThat(matchFound, is(false));

        //When
         matchFound = merchantGuide.storeIfPrice("Its my birthday");
        //Then
        assertThat(matchFound, is(false));

        //When
        matchFound = merchantGuide.answerIfHowMany("Its my birthday");
        //Then
        assertThat(matchFound, is(false));

        //When
        matchFound = merchantGuide.answerIfHowMuch("Its my birthday");
        //Then
        assertThat(matchFound, is(false));


    }

    @Test
    public void should_calculate_and_store_unit_price_of_metal() {
        //Given
        merchantGuide.storeIfSymbol("glob is I");

        //When
        boolean matchFound = merchantGuide.storeIfPrice("glob glob Silver is 34 Credits");

        //Then
        assertThat(matchFound, is(true));
        assertThat(merchantGuide.getMetalMap().get("Silver").getUnitPrice(), is(17.0f));

    }

    @Test
    public void should_convert_galactic_to_decimal_when_ask_how_much() {
        merchantGuide.storeIfSymbol("glob is I");
        merchantGuide.storeIfSymbol("pish is X");
        merchantGuide.storeIfSymbol("tegj is L");

        boolean matchFound = merchantGuide.answerIfHowMuch("how much is pish tegj glob glob ?");

        assertThat(matchFound, is(true));
        assertThat(merchantGuide.getAnswers(), hasItem("pish tegj glob glob is 42"));
    }

    @Test
    public void should_convert_galactic_to_decimal_when_ask_how_many() {
        merchantGuide.storeIfSymbol("glob is I");
        merchantGuide.storeIfSymbol("prok is V");
        merchantGuide.storeIfPrice("glob glob Silver is 34 Credits");

        boolean matchFound = merchantGuide.answerIfHowMany("how many Credits is glob prok Silver ?");

        assertThat(matchFound, is(true));
        assertThat(merchantGuide.getAnswers(), hasItem("glob prok Silver is 68 Credits"));
    }

    @Test
    public void answer_unknown() {

        merchantGuide.processUnknown();

        assertThat(merchantGuide.getAnswers(), hasItem("I have no idea what you are talking about"));
    }


}