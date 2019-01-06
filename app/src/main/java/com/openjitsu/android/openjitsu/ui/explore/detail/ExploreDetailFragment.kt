package com.openjitsu.android.openjitsu.ui.explore.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.data.repositories.CommentRepository
import com.openjitsu.android.openjitsu.data.repositories.PositionRepository
import com.openjitsu.android.openjitsu.data.repositories.SubmissionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_explore_detail.view.*
import kotlinx.android.synthetic.main.explore_detail.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * A fragment representing a single Explore detail screen.
 * This fragment is either contained in a [ExploreListActivity]
 * in two-pane mode (on tablets) or a [ExploreDetailActivity]
 * on handsets.
 */
class ExploreDetailFragment : Fragment() {

    @Inject
    lateinit var positionRepository: PositionRepository

    @Inject
    lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        Application.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.explore_detail, container, false)


        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                val id = it.getString(ARG_ITEM_ID) ?: "-1"
                Log.i("data", "ARG_ITEM_ID " + it.getString(ARG_ITEM_ID))

                coroutineScope.launch {
                    val item = withContext(Dispatchers.IO) {
                        positionRepository.getPositionById(id)
                    }
                    item.run {
                        rootView.introduction.text = item.description
                        rootView.content.text = item.content
                    }
                }
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
