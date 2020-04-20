package com.project.shoppingcart.validator.util.matchers;


import org.apache.commons.collections4.CollectionUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;

public class IsNotEmpty extends TypeSafeMatcher<Collection> {

    @Override
    protected boolean matchesSafely(Collection collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("not empty");
    }
}

