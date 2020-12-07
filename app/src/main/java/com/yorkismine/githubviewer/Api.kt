package com.yorkismine.githubviewer

import com.yorkismine.githubviewer.entity.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

interface Api {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("/repositories")
    fun getRepos(@Query("since") since: Long): Call<ArrayList<Repo>>
}