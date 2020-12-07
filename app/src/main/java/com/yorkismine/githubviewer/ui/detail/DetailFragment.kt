package com.yorkismine.githubviewer.ui.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.yorkismine.githubviewer.R
import com.yorkismine.githubviewer.base.BaseFragment
import com.yorkismine.githubviewer.entity.Repo

class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    companion object {
        val TAG: String = DetailFragment::class.java.simpleName
        const val DATA_KEY = "repo"

        fun newInstance(repo: Repo): DetailFragment {
            val fragment =
                DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(DATA_KEY, repo)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val repo = arguments?.getParcelable<Repo>(DATA_KEY)!!
        depended.showToolbar(repo.name)
        depended.setData(repo.htmlUrl)

        val web = view?.findViewById<WebView>(R.id.web)

        web?.apply {

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.findViewById<ProgressBar>(R.id.progress_detail)?.visibility = View.GONE
                    visibility = View.VISIBLE
                }
            }
            loadUrl(repo.htmlUrl)
        }


    }

}