package com.galaxy.validation;

import com.galaxy.validation.SubtractionValidator;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SubtractionValidatorTest {

    @Test
    public void I_can_be_subtracted_from_V_and_X() {
        assertThat(new SubtractionValidator("IV").validate(), is(true));
        assertThat(new SubtractionValidator("IX").validate(), is(true));
    }

    @Test
    public void I_can_not_be_subtracted_from_LCD_or_M() {
        assertThat(new SubtractionValidator("IL").validate(), is(false));
        assertThat(new SubtractionValidator("IC").validate(), is(false));
        assertThat(new SubtractionValidator("ID").validate(), is(false));
        assertThat(new SubtractionValidator("IM").validate(), is(false));
    }

    @Test
    public void X_can_be_subtracted_from_L_and_C() {
        assertThat(new SubtractionValidator("XL").validate(), is(true));
        assertThat(new SubtractionValidator("XC").validate(), is(true));
    }

    @Test
    public void X_can_not_be_subtracted_from_D_or_M() {
        assertThat(new SubtractionValidator("XD").validate(), is(false));
        assertThat(new SubtractionValidator("XM").validate(), is(false));
    }

    @Test
    public void C_can_be_subtracted_from_D_and_M() {
        assertThat(new SubtractionValidator("CD").validate(), is(true));
        assertThat(new SubtractionValidator("CM").validate(), is(true));
    }

    @Test
    public void V_can_never_be_subtracted() {
        assertThat(new SubtractionValidator("VX").validate(), is(false));
        assertThat(new SubtractionValidator("VL").validate(), is(false));
        assertThat(new SubtractionValidator("VC").validate(), is(false));
        assertThat(new SubtractionValidator("VD").validate(), is(false));
        assertThat(new SubtractionValidator("VM").validate(), is(false));
    }

    @Test
    public void L_can_never_be_subtracted() {
        assertThat(new SubtractionValidator("LC").validate(), is(false));
        assertThat(new SubtractionValidator("LD").validate(), is(false));
        assertThat(new SubtractionValidator("LM").validate(), is(false));
    }

    @Test
    public void D_can_never_be_subtracted() {
        assertThat(new SubtractionValidator("DM").validate(), is(false));
    }
}