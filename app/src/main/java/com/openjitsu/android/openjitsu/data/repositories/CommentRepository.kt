package com.openjitsu.android.openjitsu.data.repositories

import android.util.Log
import com.openjitsu.android.openjitsu.Application
import com.openjitsu.android.openjitsu.data.models.Comment
import com.openjitsu.android.openjitsu.data.models.Position
import com.openjitsu.android.openjitsu.data.network.Api
import com.openjitsu.android.openjitsu.util.NoConnectivityException
import javax.inject.Inject

class CommentRepository: Repository() {

    init {
        Application.appComponent.inject(this)
    }

    suspend fun getCommentsByPositionId(id: String): List<Comment> {
        return try {
            Log.i("openjitsu/repo", "Attempting to get comments for position id: '$id' from CommentsRepository...")
            val comments = api.getCommentsByPositionId(id).await()
            comments
        } catch (e: NoConnectivityException) {
            Log.i("openjitsu/repo", "No network connectivity, returning saved positions")
            dao.getCommentsByPositionId(id)
        }
    }
}