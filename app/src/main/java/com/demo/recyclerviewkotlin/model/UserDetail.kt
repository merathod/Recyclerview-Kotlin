package com.demo.recyclerviewkotlin.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class UserDetail(val id: Int , val name : String , val address: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDetail> {
        override fun createFromParcel(parcel: Parcel): UserDetail {
            return UserDetail(parcel)
        }

        override fun newArray(size: Int): Array<UserDetail?> {
            return arrayOfNulls(size)
        }
    }
}