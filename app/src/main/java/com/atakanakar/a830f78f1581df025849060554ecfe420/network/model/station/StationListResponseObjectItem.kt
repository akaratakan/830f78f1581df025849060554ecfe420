package com.atakanakar.a830f78f1581df025849060554ecfe420.network.model.station


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



@Entity(tableName = "favourite_planet")
@JsonClass(generateAdapter = true)
data class StationListResponseObjectItem(

    @PrimaryKey
    @Json(name = "name")
    val name: String,
    @Json(name = "coordinateX")
    val coordinateX: Double,
    @Json(name = "coordinateY")
    val coordinateY: Double,
    @Json(name = "capacity")
    val capacity: Int,
    @Json(name = "stock")
    val stock: Int,
    @Json(name = "need")
    val need: Int,
    @SerializedName("isFavourite")
    var isFavourite: Boolean = false
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(coordinateX)
        parcel.writeDouble(coordinateY)
        parcel.writeInt(capacity)
        parcel.writeInt(stock)
        parcel.writeInt(need)
        parcel.writeByte(if (isFavourite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StationListResponseObjectItem> {
        override fun createFromParcel(parcel: Parcel): StationListResponseObjectItem {
            return StationListResponseObjectItem(parcel)
        }

        override fun newArray(size: Int): Array<StationListResponseObjectItem?> {
            return arrayOfNulls(size)
        }
    }
}