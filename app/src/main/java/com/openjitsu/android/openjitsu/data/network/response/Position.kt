package com.openjitsu.android.openjitsu.data.network.response

import android.os.Parcel
import android.os.Parcelable
import com.openjitsu.android.openjitsu.data.network.response.ExploreItem

data class Position(
        override val id: String,
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

    companion object CREATOR : Parcelable.Creator<Position> {
        override fun createFromParcel(parcel: Parcel): Position {
            return Position(parcel)
        }

        override fun newArray(size: Int): Array<Position?> {
            return arrayOfNulls(size)
        }
    }
}