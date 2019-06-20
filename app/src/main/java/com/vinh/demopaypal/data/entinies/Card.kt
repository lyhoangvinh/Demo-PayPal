package com.vinh.demopaypal.data.entinies

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity
data class Card(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var token: String? = "",

    var last4: String? = "",

    var brand: String? = "",

    var country: String? = "",

    var cvcCheck: String? = "",

    var expMonth: Int? = 0,

    var expYear: Int = 0,

    var name: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(token)
        parcel.writeString(last4)
        parcel.writeString(brand)
        parcel.writeString(country)
        parcel.writeString(cvcCheck)
        parcel.writeValue(expMonth)
        parcel.writeInt(expYear)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}