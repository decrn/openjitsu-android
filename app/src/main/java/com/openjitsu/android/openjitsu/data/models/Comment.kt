package com.openjitsu.android.openjitsu.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
class Comment(
        @PrimaryKey val id: String,
        val positionId: String,
        val username: String,
        val timestamp: Int,
        val content: String
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
            parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(positionId)
        parcel.writeString(username)
        parcel.writeInt(timestamp)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }

}