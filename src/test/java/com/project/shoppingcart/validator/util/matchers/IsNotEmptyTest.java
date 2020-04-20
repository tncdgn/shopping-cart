package com.project.shoppingcart.validator.util.matchers;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsNotEmptyTest {


    @Test
    public void shouldReturnTrueIfListIsNotEmpty() {
        IsNotEmpty isNotEmpty = new IsNotEmpty();
        List<String> strings = Arrays.asList("string");

        assertTrue(isNotEmpty.matchesSafely(strings));
    }
}