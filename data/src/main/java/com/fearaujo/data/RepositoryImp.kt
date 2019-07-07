package com.fearaujo.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fearaujo.data.local.LocalRepository
import com.fearaujo.data.remote.RemoteRepository
import com.fearaujo.data.util.*
import com.fearaujo.model.Venue
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit.MILLISECONDS

object VenuesConfig {
    const val LIMIT_DEFAULT = 15
    const val DISTANCE_DEFAULT = 1000
}

class RepositoryImp(private val context: Context,
                    private val local: LocalRepository,
                    private val remote: RemoteRepository,
                    private val subscribeScheduler: Scheduler,
                    private val observeScheduler: Scheduler) : Repository {

    private val venuesLiveData = MutableLiveData<List<Venue>>()
    private val networkLiveData = MutableLiveData<Int>()
    private val compositeDisposable = CompositeDisposable()

    private var lastPrefix = ""

    override fun subscribeVenues(): LiveData<List<Venue>> {
        return venuesLiveData
    }

    override fun unsubscribeAll() {
        context.unregisterReceiver(networkChangeReceiver)
        compositeDisposable.clear()
    }

    override fun fetchVenues(searchPrefix: String, limit: Int, distance: Int) {
        val status = NetworkUtil.getConnectivityStatus(context)
        if (status == TYPE_WIFI || status == TYPE_MOBILE) {
            compositeDisposable.addAll(remote.fetchVenues(searchPrefix, limit, distance)
                    .doOnNext { venues ->
                        local.saveVenues(venues)
                    }
                    .debounce(500, MILLISECONDS)
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
                    .subscribe({ venues ->
                        venuesLiveData.postValue(venues)
                    }, {
                        Timber.e(it.localizedMessage)
                        doLocalSearch(searchPrefix)
                    }))
        } else {
            compositeDisposable.addAll(doLocalSearch(searchPrefix))
        }

        lastPrefix = searchPrefix
    }

    private fun doLocalSearch(searchPrefix: String): Disposable? {
        return local.fetchVenues("$searchPrefix%")
                .debounce(500, MILLISECONDS)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe({ venues ->
                    venuesLiveData.postValue(venues)
                }, {
                    Timber.e(it.localizedMessage)
                })
    }

    override fun subscribeNetworkStatus(): LiveData<Int> {
        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        context.registerReceiver(networkChangeReceiver, intentFilter)

        return networkLiveData
    }

    private val networkChangeReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val status = NetworkUtil.getConnectivityStatusString(context)
            networkLiveData.postValue(status)

            if (status == NETWORK_STATUS_MOBILE || status == NETWORK_STATUS_WIFI) {
                fetchVenues(lastPrefix, VenuesConfig.LIMIT_DEFAULT, VenuesConfig.DISTANCE_DEFAULT)
            }
        }
    }

}