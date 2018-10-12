package com.fearaujo.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VenuesResult(@SerializedName("meta")
                        var meta: Meta?,
                        @SerializedName("response")
                        var response: ResponseList?) : Parcelable

@Parcelize
data class ResponseList(
        @SerializedName("venues")
        var venues: List<Venue>?) : Parcelable

@Parcelize
data class Meta(@SerializedName("code")
                var code: Int?,
                @SerializedName("requestId")
                var requestId: String?) : Parcelable

@Parcelize
@Entity
data class Venue(
        @Embedded
        @SerializedName("contact")
        var contact: Contact?,
        @SerializedName("dislike")
        var dislike: Boolean?,
        @SerializedName("shortUrl")
        var shortUrl: String?,
        @PrimaryKey
        @SerializedName("id")
        var id: String,
        @SerializedName("ok")
        var ok: Boolean?,
        @Embedded @SerializedName("likes")
        var likes: Likes?,
        @SerializedName("canonicalUrl")
        var canonicalUrl: String?,
        @SerializedName("like")
        var like: Boolean?,
        @SerializedName("verified")
        var verified: Boolean?,
        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String?,
        @Embedded(prefix = "loc_") @SerializedName("location")
        var location: Location?
) : Parcelable

@Parcelize
data class Response(@SerializedName("venue")
                    var venue: Venue?) : Parcelable

@Parcelize
data class Likes(@SerializedName("count")
                 var count: Int?) : Parcelable

@Parcelize
data class VenueDetails(@SerializedName("meta")
                        var meta: Meta?,
                        @SerializedName("response")
                        var response: Response?) : Parcelable

@Parcelize
data class Location(@SerializedName("cc")
                    var cc: String?,
                    @SerializedName("country")
                    var country: String?,
                    @SerializedName("address")
                    var address: String?,
                    @SerializedName("lng")
                    var lng: Double?,
                    @ColumnInfo(name = "city")
                    @SerializedName("city")
                    var city: String?,
                    @SerializedName("state")
                    var state: String?,
                    @SerializedName("lat")
                    var lat: Double?) : Parcelable

@Parcelize
data class Contact(@SerializedName("phone")
                   val phone: String?,
                   @SerializedName("formattedPhone")
                   val formattedPhone: String?) : Parcelable


