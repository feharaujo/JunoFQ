package com.fearaujo.model


import androidx.room.*
import com.google.gson.annotations.SerializedName

data class VenuesResult(@SerializedName("meta")
                        var meta: Meta?,
                        @SerializedName("response")
                        var response: ResponseList?)

data class ResponseList(
        @SerializedName("venues")
        var venues: List<Venue>?)

data class Meta(@SerializedName("code")
                var code: Int?,
                @SerializedName("requestId")
                var requestId: String?)

@Entity
data class Venue(
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
)


data class Response(@SerializedName("venue")
                    var venue: Venue?)


data class Likes(@SerializedName("count")
                 var count: Int?)


data class VenueDetails(@SerializedName("meta")
                        var meta: Meta?,
                        @SerializedName("response")
                        var response: Response?)


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
                    var lat: Double?)


