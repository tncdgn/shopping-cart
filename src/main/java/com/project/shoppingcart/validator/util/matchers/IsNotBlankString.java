package com.project.shoppingcart.validator.util.matchers;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsNotBlankString extends TypeSafeMatcher<String> {
    @Override
    protected boolean matchesSafely(String item) {
        return StringUtils.isNotBlank(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("not blank");
    }
}