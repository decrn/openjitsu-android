package com.openjitsu.android.openjitsu.ui.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.data.models.ExploreItem
import com.openjitsu.android.openjitsu.ui.explore.detail.ExploreDetailFragment
import kotlinx.android.synthetic.main.explore_list_content.view.*

/**
 * Adapter between the ExploreItem and the Views (xml) within the Recycler view
 */
class ExploreItemRecyclerViewAdapter(private val parentActivity: ExploreListActivity,
                                     private var values: List<ExploreItem>,
                                     private val twoPane: Boolean) :
        RecyclerView.Adapter<ExploreItemRecyclerViewAdapter.ViewHolder>() {

    /**
     * onClickListener listens to clicks on the View, and internally changes it to a click
     * on a specific item view, so that recycled views don't leave behind old listeners.
     */
    private val onClickListener: View.OnClickListener
    private var allItems: List<ExploreItem>? = null

    init {
        allItems = values
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as ExploreItem
            if (twoPane) { // show detail fragment on seperate container in current activity
                val fragment = ExploreDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ExploreDetailFragment.ARG_ITEM_ID, item.id)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.explore_detail_container, fragment)
                        .commit()
            } else { // open a seperate activity with the detail fragment
                val intent = Intent(v.context, ExploreDetailActivity::class.java).apply {
                    putExtra(ExploreDetailFragment.ARG_ITEM_ID, item.id)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.name
        holder.descriptionView.text = item.description

        Glide.with(parentActivity)
                .load(item.image)
                .into(holder.thumbView)

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.explore_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = values.size

    fun replaceItems(exploreList: List<ExploreItem>) {
        this.allItems = exploreList
        this.values = exploreList
        this.notifyDataSetChanged()
    }

    fun filter(query: String = "") {
        this.values = allItems!!.filter { i ->
            i.name.toLowerCase().contains(query.toLowerCase())
                || i.type.toLowerCase().contains(query.toLowerCase())
        }
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.name
        val descriptionView: TextView = view.description
        val thumbView: AppCompatImageView = view.thumbnail
    }
}