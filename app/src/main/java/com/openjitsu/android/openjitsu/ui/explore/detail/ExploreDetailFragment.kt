package com.openjitsu.android.openjitsu.ui.explore.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.data.repositories.CommentRepository
import com.openjitsu.android.openjitsu.data.repositories.PositionRepository
import com.openjitsu.android.openjitsu.data.repositories.SubmissionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    private var listener: ExploreDetailFragment.OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Application.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.explore_detail, container, false)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_NAME)) {
                this.listener!!.setTitle(it.getString(ARG_ITEM_NAME)!!)
            }
            if (it.containsKey(ARG_ITEM_ID)) {
                val id = it.getString(ARG_ITEM_ID) ?: "-1"
                Log.i("data", "ARG_ITEM_ID " + it.getString(ARG_ITEM_ID))

                /*
                 * Launch a new CoRoutine on a seperate thread to handle passing the data
                 * retrieved from the Repository to the adapter
                 */
                coroutineScope.launch {
                    val item = withContext(Dispatchers.IO) {
                        positionRepository.getPositionById(id)
                    }
                    item.run {
                        rootView.findViewById<TextView>(R.id.introduction).text = item.description
                        rootView.findViewById<TextView>(R.id.content).text = item.content
//                        rootView.introduction.text = item.description
//                        rootView.content.text = item.content
                    }
                }
            }
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ExploreDetailFragment.OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun setTitle(title: String)
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
        const val ARG_ITEM_NAME = "item_name"
    }
}
