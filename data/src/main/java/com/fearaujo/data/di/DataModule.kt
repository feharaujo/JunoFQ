package com.fearaujo.data.di

import com.fearaujo.data.Repository
import com.fearaujo.data.RepositoryImp
import com.fearaujo.data.local.LocalRepository
import com.fearaujo.data.remote.ApiService
import com.fearaujo.data.remote.BASE_URL
import com.fearaujo.data.remote.RemoteRepository
import com.google.gson.Gson
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Params {
    const val OAUTH_TOKEN = "oauth_token"

    const val MAIN_REPOSITORY = "main_repository"
    const val LOCAL_REPOSITORY = "local_repository"
    const val REMOTE_REPOSITORY = "remote_repository"
}

val repositoryModule = module {
    // Main Repository
    single(Params.MAIN_REPOSITORY) {
        RepositoryImp(get(name = Params.LOCAL_REPOSITORY), get(name = Params.REMOTE_REPOSITORY)) as Repository
    }

    // Local repository
    single(Params.LOCAL_REPOSITORY) {
        LocalRepository() as Repository
    }

    // Remote repository
    single(Params.REMOTE_REPOSITORY) {
        RemoteRepository(getProperty(Params.OAUTH_TOKEN), get()) as Repository
    }

    single {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(BASE_URL)
                .build()

        retrofit.create(ApiService::class.java)
    }

}