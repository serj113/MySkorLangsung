package com.setia.myfootballmatch


import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView

@RunWith(AndroidJUnit4::class)
class FavoritesTest {

    @Rule
    @JvmField var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun favoritesTest() {
        onView(withId(R.id.list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        onView(
                withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(500)

        onView(withText("Added to favorite")).check(matches(isDisplayed()))

    }

    @Test
    fun nextMatchFavoritesTest() {
        onView(withId(R.id.navigation_next)).perform(click())
        onView(withId(R.id.list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        onView(
                withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(500)

        onView(withText("Added to favorite")).check(matches(isDisplayed()))

    }

    @Test
    fun removeFavoritesTest() {
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
        onView(
                withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(500)

        onView(withText("Removed from favorite")).check(matches(isDisplayed()))

    }

}
