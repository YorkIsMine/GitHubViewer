package com.yorkismine.githubviewer

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<FetchReposInteractor> { FetchReposInteractor(get()) }

    single<Api> {
        Retrofit.Builder().baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }
}