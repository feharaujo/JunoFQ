package com.fearaujo.data.remote

import com.fearaujo.model.VenueDetails
import com.fearaujo.model.VenuesResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ENDPOINT_SEARCH)
    fun fetchVenues(@Query(PARAM_OAUTH_TOKEN) token: String,
                    @Query(PARAM_NEAR) near: String,
                    @Query(PARAM_VERSION) version: String,
                    @Query(PARAM_LIMIT) limit: Int,
                    @Query(PARAM_DISTANCE) distance: Int
    ): Observable<VenuesResult>

    @GET(ENDPOINT_DETAILS)
    fun fetchDetails(@Path(PARAM_ID) id: String,
                     @Query(PARAM_OAUTH_TOKEN) token: String,
                     @Query(PARAM_VERSION) version: String
    ): Observable<VenueDetails>

}