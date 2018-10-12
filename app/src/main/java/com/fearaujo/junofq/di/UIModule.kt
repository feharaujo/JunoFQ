package com.fearaujo.junofq.di

import com.fearaujo.junofq.dashboard.DashboardPresenter
import com.fearaujo.junofq.dashboard.VenueListAdapter
import org.koin.dsl.module.module

object UIParams {
    const val VIEW = "view"
}

val appModule = module {
    factory { DashboardPresenter(get(UIParams.VIEW), get()) }
}