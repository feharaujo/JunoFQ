package com.fearaujo.junofq.dashboard

import com.fearaujo.data.Repository
import com.fearaujo.data.VenuesConfig
import com.fearaujo.data.util.NETWORK_STATUS_MOBILE
import com.fearaujo.data.util.NETWORK_STATUS_WIFI
import com.fearaujo.data.util.NetworkUtil
import com.fearaujo.mememaker.arch.BasePresenter

class DashboardPresenter(override var view: DashboardContract.View?, private val repository: Repository) :
        BasePresenter<DashboardContract.View>(), DashboardContract.Presenter {


    override fun onPresentCreated() {
        super.onPresentCreated()

        repository.subscribeVenues().observeForever { items ->
            view?.updateItems(items)
        }

        repository.subscribeNetworkStatus().observeForever {status ->
            if(status == NETWORK_STATUS_MOBILE || status == NETWORK_STATUS_WIFI) {
                view?.hideNoConnectionWarning()
            } else {
                view?.showNoConnectionWarning()
            }
        }
    }

    override fun onPresenterDestroy() {
        repository.unsubscribeAll()
        super.onPresenterDestroy()
    }

    override fun fetchVenues(prefix: String) {
        repository.fetchVenues(prefix, VenuesConfig.LIMIT_DEFAULT, VenuesConfig.DISTANCE_DEFAULT)
    }

}