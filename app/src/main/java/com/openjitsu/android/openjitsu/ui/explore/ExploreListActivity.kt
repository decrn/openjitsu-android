package com.openjitsu.android.openjitsu.ui.explore

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.openjitsu.android.openjitsu.R

import kotlinx.android.synthetic.main.activity_explore_list.*
import kotlinx.android.synthetic.main.explore_list.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.data.models.ExploreItem
import com.openjitsu.android.openjitsu.data.repositories.PositionRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.content.Intent
import com.openjitsu.android.openjitsu.ui.user.UserActivity


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ExploreDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ExploreListActivity : AppCompatActivity() {

    @Inject
    lateinit var positionRepository: PositionRepository

    @Inject
    lateinit var coroutineScope: CoroutineScope

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    /**
     * CompositeDisposable to help manage subscriptions when the Activity is closed
     * to prevent memory leaks
     */
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_list)

        // Inject the dependencies
        Application.appComponent.inject(this)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (explore_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        explore_list.layoutManager = LinearLayoutManager(this)
        val adapter = ExploreItemRecyclerViewAdapter(this, emptyList(), this.twoPane)
        Application.appComponent.inject(adapter)

        coroutineScope.launch {
            val items = withContext(Dispatchers.IO) {
                positionRepository.getAllPositions()
            }
            items.run {
                Log.i("openjitsu/data", "Found " + items.size + " items!")
                adapter.replaceItems(items)
            }
        }

        explore_list.adapter = adapter
    }

    fun onClose() {
        compositeDisposable.dispose()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.action_search)?.actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            // setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            maxWidth = Integer.MAX_VALUE
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    (explore_list.adapter as ExploreItemRecyclerViewAdapter).filter(query ?: "")
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    // filter recycler view when text is changed
                    (explore_list.adapter as ExploreItemRecyclerViewAdapter).filter(query ?: "")
                    return false
                }
            })
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_profile -> startActivity(Intent(this, UserActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

}
