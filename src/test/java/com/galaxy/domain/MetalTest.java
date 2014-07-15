package com.galaxy.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MetalTest {

    @Test
    public void should_return_Silver() {
        Metal metal = Metal.detect("Silver");
        assertThat(metal instanceof Silver, is(true));
    }

    @Test
    public void should_return_Gold() {
        Metal metal = Metal.detect("Gold");
        assertThat(metal instanceof Gold, is(true));
    }

    @Test
    public void should_return_Iron() {
        Metal metal = Metal.detect("Iron");
        assertThat(metal instanceof Iron, is(true));
    }

}