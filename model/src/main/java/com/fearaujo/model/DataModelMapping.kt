package com.fearaujo.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MetaResponse(@SerializedName("meta")
                        val meta: Meta?,
                        @SerializedName("response")
                        val response: Response?)

data class Meta(@SerializedName("code")
                val code: Int? = 0,
                @SerializedName("requestId")
                val requestId: String? = "")

data class Geocode(@SerializedName("what")
                   val what: String? = "",
                   @SerializedName("feature")
                   val feature: Feature?,
                   @SerializedName("where")
                   val where: String? = "")


data class VenuesItem(@SerializedName("hasPerk")
                      val hasPerk: Boolean? = false,
                      @SerializedName("referralId")
                      val referralId: String? = "",
                      @SerializedName("name")
                      val name: String? = "",
                      @SerializedName("location")
                      val location: Location?,
                      @SerializedName("id")
                      val id: String? = "",
                      @SerializedName("categories")
                      val categories: List<CategoriesItem>??)


data class Center(@SerializedName("lng")
                  val lng: Double? = 0.0,
                  @SerializedName("lat")
                  val lat: Double? = 0.0)


data class Sw(@SerializedName("lng")
              val lng: Double? = 0.0,
              @SerializedName("lat")
              val lat: Double? = 0.0)


data class Bounds(@SerializedName("sw")
                  val sw: Sw?,
                  @SerializedName("ne")
                  val ne: Ne?)


data class CategoriesItem(@SerializedName("pluralName")
                          val pluralName: String? = "",
                          @SerializedName("name")
                          val name: String? = "",
                          @SerializedName("icon")
                          val icon: Icon?,
                          @SerializedName("id")
                          val id: String? = "",
                          @SerializedName("shortName")
                          val shortName: String? = "",
                          @SerializedName("primary")
                          val primary: Boolean? = false)


data class Feature(@SerializedName("cc")
                   val cc: String? = "",
                   @SerializedName("woeType")
                   val woeType: Int? = 0,
                   @SerializedName("highlightedName")
                   val highlightedName: String? = "",
                   @SerializedName("displayName")
                   val displayName: String? = "",
                   @SerializedName("name")
                   val name: String? = "",
                   @SerializedName("geometry")
                   val geometry: Geometry?,
                   @SerializedName("id")
                   val id: String? = "",
                   @SerializedName("longId")
                   val longId: String? = "",
                   @SerializedName("matchedName")
                   val matchedName: String? = "",
                   @SerializedName("slug")
                   val slug: String? = "")


data class Geometry(@SerializedName("center")
                    val center: Center?,
                    @SerializedName("bounds")
                    val bounds: Bounds?)


data class Response(@SerializedName("confident")
                    val confident: Boolean? = false,
                    @SerializedName("geocode")
                    val geocode: Geocode?,
                    @SerializedName("venues")
                    val venues: List<VenuesItem>??)

data class Ne(@SerializedName("lng")
              val lng: Double? = 0.0,
              @SerializedName("lat")
              val lat: Double? = 0.0)


data class Icon(@SerializedName("prefix")
                val prefix: String? = "",
                @SerializedName("suffix")
                val suffix: String? = "")


data class Location(@SerializedName("cc")
                    val cc: String? = "",
                    @SerializedName("country")
                    val country: String? = "",
                    @SerializedName("address")
                    val address: String? = "",
                    @SerializedName("lng")
                    val lng: Double? = 0.0,
                    @SerializedName("formattedAddress")
                    val formattedAddress: List<String>??,
                    @SerializedName("city")
                    val city: String? = "",
                    @SerializedName("state")
                    val state: String? = "",
                    @SerializedName("lat")
                    val lat: Double? = 0.0)


