package com.openjitsu.android.openjitsu.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "submissions")
data class Submission(
        @PrimaryKey override val id: String,
        override val name: String,
        override val description: String,
        override val image: String,
        override val content: String
//        val related: String[],
//        val comments: Comment[]
) : Parcelable, ExploreItem {

    // Parcelable
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
//        parcel.readString(),
//        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(content)
//        parcel.writeString(related)
//        parcel.writeString(comments)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Submission> {
        override fun createFromParcel(parcel: Parcel): Submission {
            return Submission(parcel)
        }

        override fun newArray(size: Int): Array<Submission?> {
            return arrayOfNulls(size)
        }
    }
}