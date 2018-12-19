package com.openjitsu.android.openjitsu.ui.explore.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.ui.explore.ExploreViewModel

class PositionsFragment : Fragment() {

    companion object {
        fun newInstance() = PositionsFragment()
    }

    private lateinit var viewModel: ExploreViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.position_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
