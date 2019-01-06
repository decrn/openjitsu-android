package com.openjitsu.android.openjitsu.ui.explore.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.data.models.Comment
import com.openjitsu.android.openjitsu.data.repositories.CommentRepository
import com.openjitsu.android.openjitsu.data.repositories.PositionRepository
import com.openjitsu.android.openjitsu.ui.explore.ExploreItemRecyclerViewAdapter
import kotlinx.android.synthetic.main.explore_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CommentFragment.OnListFragmentInteractionListener] interface.
 */
class CommentFragment : Fragment() {

    @Inject
    lateinit var commentRepository: CommentRepository

    @Inject
    lateinit var coroutineScope: CoroutineScope

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inject the dependencies
        Application.appComponent.inject(this)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_comment_sheet, container, false)

        val list = view.findViewById<RecyclerView>(R.id.list)

        val adapter = CommentRecyclerViewAdapter(emptyList(), listener)
        list.adapter = adapter
        Application.appComponent.inject(adapter)

        coroutineScope.launch {
            val items = withContext(Dispatchers.IO) {
                val id = arguments?.getString(ExploreDetailFragment.ARG_ITEM_ID) ?: "-1"
                commentRepository.getCommentsByPositionId(id)
            }
            items.run {
                Log.i("openjitsu/data", "Found " + items.size + " items!")
                adapter.replaceItems(items)
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Comment?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                CommentFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
