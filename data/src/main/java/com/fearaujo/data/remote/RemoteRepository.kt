package com.fearaujo.data.remote

import com.fearaujo.data.Repository
import retrofit2.Retrofit

class RemoteRepository(private val oauthToken: String, private val service: ApiService) : Repository {



}