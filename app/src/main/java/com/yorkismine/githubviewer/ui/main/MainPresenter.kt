package com.yorkismine.githubviewer.ui.main

import com.yorkismine.githubviewer.FetchReposInteractor
import com.yorkismine.githubviewer.base.BasePresenter
import com.yorkismine.githubviewer.entity.Repo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MainPresenter : BasePresenter<MainView>(), KoinComponent {

    private val interactor: FetchReposInteractor by inject()

    fun loadData(since: Long) {
        view?.showProgress()
        interactor.fetchRepos(since).enqueue(object : Callback<ArrayList<Repo>> {
            override fun onFailure(call: Call<ArrayList<Repo>>, t: Throwable) {
                view?.showError(t)
            }

            override fun onResponse(call: Call<ArrayList<Repo>>, response: Response<ArrayList<Repo>>) {
                response.body()?.also {
                    view?.displayRepos(it)
                }

                view?.hideProgress()
            }

        })

    }
}