package com.openjitsu.android.openjitsu

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import junit.framework.Assert.assertTrue
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`

/*
 * https://stackoverflow.com/a/39446889
 *
 * Usage:
 *
 * onView(withId(R.id.recyclerView)).check(new RecyclerViewItemCountAssertion(5));
 * onView(withId(R.id.recyclerView)).check(new RecyclerViewItemCountAssertion(greaterThan(5));
 * onView(withId(R.id.recyclerView)).check(new RecyclerViewItemCountAssertion(lessThan(5));
 */
class RecyclerViewItemCountAssertion private constructor(private val matcher: Matcher<Int>) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter!!.itemCount, matcher)
    }

    companion object {

        fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
            return withItemCount(`is`(expectedCount))
        }

        fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
            return RecyclerViewItemCountAssertion(matcher)
        }
    }
}