package com.fearaujo.data

import androidx.lifecycle.LiveData
import com.fearaujo.model.Venue
import com.fearaujo.model.VenueDetails
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {

    fun subscribeVenues() : LiveData<List<Venue>>

    fun subscribeNetworkStatus() : LiveData<Int>

    fun unsubscribeAll()

    fun fetchVenues(searchPrefix: String, limit: Int, distance: Int)

}