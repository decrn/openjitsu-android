package com.openjitsu.android.openjitsu.ui.explore

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.ui.explore.detail.CommentFragment
import com.openjitsu.android.openjitsu.ui.explore.detail.ExploreDetailFragment


/**
 * An activity representing a single Explore detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ExploreListActivity].
 */
class ExploreDetailActivity : AppCompatActivity(), ExploreDetailFragment.OnFragmentInteractionListener, CommentFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val contentFragment = ExploreDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ExploreDetailFragment.ARG_ITEM_ID,
                            intent.getStringExtra(ExploreDetailFragment.ARG_ITEM_ID))
                    putString(ExploreDetailFragment.ARG_ITEM_NAME,
                            intent.getStringExtra(ExploreDetailFragment.ARG_ITEM_NAME))
                }
            }

            val commentFragment = CommentFragment().apply {
                arguments = Bundle().apply {
                    putString(ExploreDetailFragment.ARG_ITEM_ID,
                            intent.getStringExtra(ExploreDetailFragment.ARG_ITEM_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.explore_detail_container, contentFragment)
                    .add(R.id.comments_sheet, commentFragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, ExploreListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun setTitle(title: String) {
        val mActionBarToolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(mActionBarToolbar)
        getSupportActionBar()!!.setTitle(title)
    }

}
