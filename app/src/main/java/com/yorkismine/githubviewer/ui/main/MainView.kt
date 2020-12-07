package com.yorkismine.githubviewer.ui.main

import com.yorkismine.githubviewer.base.BaseView
import com.yorkismine.githubviewer.entity.Repo

interface MainView : BaseView {
    fun showProgress()
    fun hideProgress()
    fun displayRepos(repos: ArrayList<Repo>)
}