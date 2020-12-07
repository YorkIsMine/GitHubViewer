package com.yorkismine.githubviewer.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment(contentLayoutId: Int = 0) : Fragment(contentLayoutId) {
    lateinit var depended: Depended

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        depended = (activity as Depended)
    }
}