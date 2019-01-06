package com.openjitsu.android.openjitsu

import androidx.test.rule.ActivityTestRule

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.openjitsu.android.openjitsu.ui.explore.ExploreItemRecyclerViewAdapter
import com.openjitsu.android.openjitsu.ui.explore.ExploreListActivity
import junit.framework.Assert.*


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
public class InstrumentedTests {

    @get:Rule
    public var exploreListActivity: ActivityTestRule<ExploreListActivity> = ActivityTestRule(ExploreListActivity::class.java)

    @Test
    fun loadsExploreItemList() {
        Thread.sleep(1000)
        val recyclerView = exploreListActivity.activity.findViewById<RecyclerView>(R.id.explore_list)
        assertTrue(recyclerView.adapter!!.itemCount > 0)
    }

    @Test
    fun searchFilterCanMatchExistingItem() {
        typeInSearchBar("Full guard", 1)
    }

    @Test
    fun searchFilterMatchesWrongCase() {
        typeInSearchBar("fUlL gUArD", 1)
    }

    @Test
    fun searchFilterDoesntMatchInvalidQuery() {
        typeInSearchBar("London Eye", 0)
    }

    private fun typeInSearchBar(query: String, expectedCount: Int) {
        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.search_src_text)).perform(typeText(query))
        onView(withId(R.id.explore_list)).check(RecyclerViewItemCountAssertion.withItemCount(expectedCount))
    }

    private fun openDetail(index: Int) {
        onView(withId(R.id.explore_list)).perform(RecyclerViewActions.actionOnItemAtPosition<ExploreItemRecyclerViewAdapter.ViewHolder>(index, click()))
    }

    @Test
    fun loadsCommentsForFullGuard() {
        typeInSearchBar("Full guard", 1)
        openDetail(0)
        Thread.sleep(1000)
        onView(withId(R.id.comment_list)).check(RecyclerViewItemCountAssertion.withItemCount(1))
    }


}
/*
* Simpele test: ExploreItems laden
* Filters testen
* Testen of comments geladen worden
* After rotate?
* Switch login/register test (simpel)
* Profile button in toolbar loads something
 */