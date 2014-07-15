package com.galaxy.validation;

import com.galaxy.validation.RepetitionValidator;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RepetitionValidatorTest {


    @Test
    public void should_return_invalid_if_X_repeated_four_times_in_succession() throws Exception {
        boolean valid = new RepetitionValidator("XXXX").validate();
        assertThat(valid, is(false));
    }

    @Test
    public void should_return_valid_if_X_repeated_less_than_four_times() throws Exception {
        boolean valid = new RepetitionValidator("X").validate();
        assertThat(valid, is(true));
    }

    @Test
    public void should_return_valid_if_X_repeated_four_times_not_in_succession() throws Exception {
        boolean valid = new RepetitionValidator("XXXIX").validate();
        assertThat(valid, is(true));
    }

    @Test
    public void should_return_invalid_if_I_repeated_four_times_in_succession() throws Exception {
        boolean valid = new RepetitionValidator("IIIII").validate();
        assertThat(valid, is(false));
    }

    @Test
    public void should_return_valid_if_I_repeated_less_than_four_times() throws Exception {
        boolean valid = new RepetitionValidator("III").validate();
        assertThat(valid, is(true));
    }

    @Test
    public void should_return_invalid_if_C_repeated_more_than_three_times_in_succession() throws Exception {
        boolean valid = new RepetitionValidator("CCCCCCCC").validate();
        assertThat(valid, is(false));
    }

    @Test
    public void should_return_valid_if_C_repeated_less_than_four_times() throws Exception {
        boolean valid = new RepetitionValidator("CC").validate();
        assertThat(valid, is(true));
    }

    @Test
    public void should_return_invalid_if_M_repeated_more_than_three_times_in_succession() throws Exception {
        boolean valid = new RepetitionValidator("MMMMMMMMMMMMMMMMMM").validate();
        assertThat(valid, is(false));
    }

    @Test
    public void should_return_valid_if_M_repeated_less_than_four_times() throws Exception {
        boolean valid = new RepetitionValidator("M").validate();
        assertThat(valid, is(true));
    }

    @Test
    public void D_can_never_be_repeated_in_succession() {
        boolean valid = new RepetitionValidator("DD").validate();
        assertThat(valid, is(false));
    }

    @Test
    public void D_can_never_be_repeated_not_in_succession() {
        boolean valid = new RepetitionValidator("DIID").validate();
        assertThat(valid, is(false));
    }


    @Test
    public void D_can_appear_without_repetition() {
        boolean valid = new RepetitionValidator("DI").validate();
        assertThat(valid, is(true));
    }

    @Test
    public void L_can_never_be_repeated_in_succession() {
        boolean valid = new RepetitionValidator("LL").validate();
        assertThat(valid, is(false));
    }

    @Test
    public void L_can_never_be_repeated_not_in_succession() {
        boolean valid = new RepetitionValidator("LILI").validate();
        assertThat(valid, is(false));
    }


    @Test
    public void L_can_appear_without_repetition() {
        boolean valid = new RepetitionValidator("LI").validate();
        assertThat(valid, is(true));
    }

    @Test
    public void V_can_never_be_repeated_in_succession() {
        boolean valid = new RepetitionValidator("XVVX").validate();
        assertThat(valid, is(false));
    }

    @Test
    public void V_can_never_be_repeated_not_in_succession() {
        boolean valid = new RepetitionValidator("VIVI").validate();
        assertThat(valid, is(false));
    }

    @Test
    public void V_can_appear_without_repetition() {
        boolean valid = new RepetitionValidator("IV").validate();
        assertThat(valid, is(true));
    }


}