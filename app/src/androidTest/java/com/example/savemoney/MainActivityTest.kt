package com.example.savemoney

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.savemoney.view.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    @Test
    fun test_ApiButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.apiBtn)).perform(click())
        onView(withId(R.id.apiGoals)).check(matches(isDisplayed()))
    }
    @Test
    fun test_AddGoals() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.uploadL)).check(matches(isDisplayed()))
    }
    @Test
    fun test_YourGoals() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.listBtn)).perform(click())
        onView(withId(R.id.items)).check(matches(isDisplayed()))
    }
}