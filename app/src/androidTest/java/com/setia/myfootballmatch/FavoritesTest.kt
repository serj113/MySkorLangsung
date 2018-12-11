package com.setia.myfootballmatch


import android.support.test.espresso.Espresso.onData
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
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher

@RunWith(AndroidJUnit4::class)
class FavoritesTest {

    @Rule
    @JvmField var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun favoritesMatchTest() {
        Thread.sleep(2000)
        onView(allOf(withId(R.id.match_sp),
                childAtPosition(
                        withParent(withId(R.id.event_container_view_pager)),
                        0), isDisplayed())).perform(click())

        onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(`is`<String>("android.widget.PopupWindow\$PopupBackgroundView")),
                        0))
                .atPosition(40).perform(click())
        Thread.sleep(1000)

        onView(
                allOf(withId(R.id.match_rv),
                        isDisplayed())
        ).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(500)

        onView(withText("Added to favorite")).check(matches(isDisplayed()))

    }

    @Test
    fun teamFavoriteTest() {
        Thread.sleep(1000)
        onView(withId(R.id.navigation_teams)).perform(click())
        Thread.sleep(2000)
        onView(
                allOf(withId(R.id.team_rv),
                        isDisplayed())
        ).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(500)

        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(500)

        onView(withText("Added to favorite")).check(matches(isDisplayed()))

    }

    @Test
    fun removeFavoriteMatchTest() {
        Thread.sleep(1000)
        onView(withId(R.id.navigation_favorite)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.match_favorite_list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView(
                withId(R.id.match_favorite_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(200)

        onView(withText("Removed from favorite")).check(matches(isDisplayed()))

    }


    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
