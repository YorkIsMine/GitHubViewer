package com.yorkismine.githubviewer.base

import androidx.fragment.app.Fragment

interface Depended {
    fun goToFragment(fragment: Fragment, tag: String)
    fun hideToolbar()
    fun showToolbar(title: String)
    fun setData(url: String)
}