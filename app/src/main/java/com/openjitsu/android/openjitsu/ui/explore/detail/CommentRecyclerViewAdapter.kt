package com.openjitsu.android.openjitsu.ui.explore.detail

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.openjitsu.android.openjitsu.R
import com.openjitsu.android.openjitsu.data.models.Comment
import com.openjitsu.android.openjitsu.data.models.ExploreItem


import com.openjitsu.android.openjitsu.ui.explore.detail.CommentFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_comment.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class CommentRecyclerViewAdapter(
        private var mValues: List<Comment>,
        private val mListener: CommentFragment.OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mUsernameView.text = item.username
        holder.mTimestampView.text = formatDate(item.timestamp)
        holder.mContentView.text = item.content

        with(holder.mView) {
            tag = item
        }
    }

    // Source: https://stackoverflow.com/a/36831861
    private fun formatDate(seconds: Int): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss")
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = seconds * 1000L
        val tz = TimeZone.getDefault()
        sdf.timeZone = tz
        return sdf.format(calendar.time)
    }

    override fun getItemCount(): Int = mValues.size

    fun replaceItems(commentsList: List<Comment>) {
        this.mValues = commentsList
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mUsernameView: TextView = mView.username
        val mTimestampView: TextView = mView.timestamp
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
