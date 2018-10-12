package com.fearaujo.data.di

import androidx.room.Room
import com.fearaujo.data.Repository
import com.fearaujo.data.RepositoryImp
import com.fearaujo.data.local.AppDatabase
import com.fearaujo.data.remote.ApiService
import com.fearaujo.data.remote.BASE_URL
import com.fearaujo.data.remote.RemoteRepository
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DataParams {
    const val OAUTH_TOKEN = "oauth_token"
    const val SUBSCRIBE_SCHEDULER = "subscribe_scheduler"
    const val OBSERVE_SCHEDULER = "observe_scheduler"
}

val repositoryModule = module {
    // Main Repository
    single {
        RepositoryImp(androidContext(), get(), get(),
                get(DataParams.SUBSCRIBE_SCHEDULER), get(DataParams.OBSERVE_SCHEDULER)) as Repository
    }

    // Local repository
    factory {
        val db = Room.databaseBuilder(androidContext(),
                AppDatabase::class.java, "database-name").build()
        db.repositoryDao()
    }

    // Remote repository
    single {
        RemoteRepository(getProperty(DataParams.OAUTH_TOKEN), get())
    }

    single {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        retrofit.create(ApiService::class.java)
    }

    single(DataParams.SUBSCRIBE_SCHEDULER) {
        Schedulers.io()
    }

    single(DataParams.OBSERVE_SCHEDULER) {
        AndroidSchedulers.mainThread()
    }

}