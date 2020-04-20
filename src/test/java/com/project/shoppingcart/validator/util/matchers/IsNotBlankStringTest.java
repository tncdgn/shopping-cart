package com.project.shoppingcart.validator.util.matchers;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsNotBlankStringTest {

    @Test
    public void shouldReturnTrueIfNotBlank() {
        IsNotBlankString isNotBlankString = new IsNotBlankString();

        assertTrue(isNotBlankString.matchesSafely("item"));
    }
}