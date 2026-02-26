package com.example.mediatekformationmobile.view;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.Matchers.anything;

import com.example.mediatekformationmobile.R;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test public void testNavFormations() {
        onView(withId(R.id.btnFormations)).perform(click());
        onView(withId(R.id.btnFiltrer)).check(matches(isDisplayed()));
    }

    @Test public void testNavFavoris() {
        onView(withId(R.id.btnFavoris)).perform(click());
        onView(withId(R.id.btnFiltrer)).check(matches(isDisplayed()));
    }
}
