package com.openjitsu.android.openjitsu.ui.explore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.data.network.response.Position

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_explore_list.*
import kotlinx.android.synthetic.main.explore_list.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.openjitsu.android.openjitsu.Application
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


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
    lateinit var api : Api

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

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (explore_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        explore_list.layoutManager = LinearLayoutManager(this)
        var adapter = ExploreItemRecyclerViewAdapter(this, emptyList(), this.twoPane)
        Application.appComponent.inject(adapter)

        var positions: List<Position>

        api.getAllPositions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            Log.i("openjitsu/data", "data was loaded successfully!")
                            adapter.replaceItems(list)
                        },Throwable::printStackTrace
                )
                .addTo(compositeDisposable)


        explore_list.adapter = adapter
    }

    fun onClose() {
        compositeDisposable.dispose()
    }
}
