package com.example.namequiz

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Names(private val name:String, private val imgId: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

   fun getName(): String {
        return name
    }

    fun getImgId(): Int {
        return imgId
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(imgId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Names> {
            override fun createFromParcel(parcel: Parcel): Names {
                return Names(parcel)
            }

            override fun newArray(size: Int): Array<Names?> {
                return arrayOfNulls(size)
            }
        }
    }
}