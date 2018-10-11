package com.fearaujo.junofq

import android.app.Application
import com.fearaujo.data.di.Params
import com.fearaujo.data.di.repositoryModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }

    /**
     * Dependency injection starts after obtain the oAuthToken
     */
    fun initKoinInjection(oauthToken: String) {
        val properties = mapOf(Params.OAUTH_TOKEN to oauthToken)
        startKoin(this, modules = listOf(
                repositoryModule
        ), extraProperties = properties)
    }

}