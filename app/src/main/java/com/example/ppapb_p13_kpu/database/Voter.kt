package com.example.ppapb_p13_kpu.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voter_table")
data class Voter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "nik")
    val nik: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "address")
    val address: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(nik)
        parcel.writeString(gender)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Voter> {
        override fun createFromParcel(parcel: Parcel): Voter {
            return Voter(parcel)
        }

        override fun newArray(size: Int): Array<Voter?> {
            return arrayOfNulls(size)
        }
    }
}
