package com.fearaujo.junofq.dashboard

import com.fearaujo.mememaker.arch.BaseContract
import com.fearaujo.model.Venue

interface DashboardContract : BaseContract {

    interface View : BaseContract.View {
        fun updateItems(items: List<Venue>)

        fun showNoConnectionWarning()
        fun hideNoConnectionWarning()
    }

    interface Presenter : BaseContract.Presenter<DashboardContract.View> {
        fun fetchVenues(prefix: String)
    }

}