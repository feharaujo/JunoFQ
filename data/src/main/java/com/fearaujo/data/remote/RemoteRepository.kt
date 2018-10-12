package com.fearaujo.data.remote

import android.accounts.NetworkErrorException
import com.fearaujo.model.Venue
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

class RemoteRepository(private val oauthToken: String?, private val service: ApiService) {

    private val version by lazy {
        SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
    }

    fun fetchVenues(searchPrefix: String, limit: Int, distance: Int): Observable<List<Venue>> {
        if (oauthToken == null || oauthToken.isEmpty()) {
            throw NetworkErrorException()
        } else {
            return service.fetchVenues(oauthToken, searchPrefix, version, limit, distance)
                    .flatMap { item ->
                        Observable.fromIterable(item.response?.venues)
                    }.concatMapEager { item ->
                        item.id.let { id -> service.fetchDetails(id, oauthToken, version) }
                    }.flatMap { details ->
                        details.response?.venue?.let { venue ->
                            Observable.just(venue)
                        }
                    }
                    .toList()
                    .toObservable()
        }
    }

}