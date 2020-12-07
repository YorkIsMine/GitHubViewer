package com.yorkismine.githubviewer

import com.yorkismine.githubviewer.entity.Repo
import retrofit2.Call
import java.util.ArrayList

class FetchReposInteractor(private val service: Api) {
    fun fetchRepos(since: Long): Call<ArrayList<Repo>> {
        return service.getRepos(since)
    }
}