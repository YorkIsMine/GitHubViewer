package com.yorkismine.githubviewer.base

abstract class BasePresenter<V> where V : BaseView {
    var view: V? = null
    fun attached(view: V?) {
        this.view = view
    }

    fun detached() {
        this.view = null
    }
}