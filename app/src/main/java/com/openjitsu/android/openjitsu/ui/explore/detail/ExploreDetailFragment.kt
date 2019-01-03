package com.openjitsu.android.openjitsu.ui.explore.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.data.Api
import com.openjitsu.android.openjitsu.models.Position
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.explore_detail.view.*

/**
 * A fragment representing a single Explore detail screen.
 * This fragment is either contained in a [ExploreListActivity]
 * in two-pane mode (on tablets) or a [ExploreDetailActivity]
 * on handsets.
 */
class ExploreDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.explore_detail, container, false)


        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                Log.i("data", "ARG_ITEM_ID " + it.getString(ARG_ITEM_ID))
                Api.create().getPositionById(it.getString(ARG_ITEM_ID))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { pos ->
                                    Log.i("data", "Successfully loaded data")
                                    rootView.explore_detail.text = pos.content
                                },
                                {
                                    Log.e("data", "Failed to load data")
                                    // Catch error
                                }
                        )
            }
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
