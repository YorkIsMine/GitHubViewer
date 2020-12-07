package com.yorkismine.githubviewer.ui.main

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yorkismine.githubviewer.R
import com.yorkismine.githubviewer.ReposAdapter
import com.yorkismine.githubviewer.base.BaseFragment
import com.yorkismine.githubviewer.entity.Repo
import com.yorkismine.githubviewer.utils.OnClickListener
import com.yorkismine.githubviewer.ui.detail.DetailFragment

class MainFragment : BaseFragment(), MainView {

    companion object {
        val TAG = MainFragment::class.java.simpleName
        const val RECYCLER_STATE = "recycler_state"
        const val LIST_STATE = "list_state"
    }

    private val presenter: MainPresenter by lazy { MainPresenter() }
    private var recycler: RecyclerView? = null
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var errorTV: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.attached(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detached()
    }

    private val adapter = ReposAdapter(object :
        OnClickListener {
        override fun onClick(repo: Repo) {
            depended.goToFragment(
                DetailFragment.newInstance(repo),
                DetailFragment.TAG
            )
        }

    })

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recycler?.apply {
            val listState = layoutManager?.onSaveInstanceState()
            outState.putParcelable(RECYCLER_STATE, listState)
            outState.putParcelableArrayList(LIST_STATE, this@MainFragment.adapter.repos)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        recycler = view.findViewById(R.id.recycler)
        refreshLayout = view.findViewById(R.id.ref_layout)
        errorTV = view.findViewById(R.id.error_tv)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        depended.hideToolbar()
        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(requireContext())

        if (savedInstanceState == null)
            presenter.loadData(0)
        else {
            savedInstanceState.getParcelableArrayList<Repo>(LIST_STATE)?.let { adapter.setData(it) }
            recycler?.layoutManager?.onRestoreInstanceState(
                savedInstanceState.getParcelable<Parcelable>(
                    RECYCLER_STATE
                )
            )
        }

        recycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                (recyclerView.layoutManager as LinearLayoutManager?)?.apply {
                    if (findLastCompletelyVisibleItemPosition() == adapter.repos.size - 1) {
                        presenter.loadData(adapter.getTheLastRepo().id)
                    }
                }
            }
        })

        refreshLayout.setOnRefreshListener {
            presenter.loadData(0)
        }
    }

    override fun showProgress() {
        refreshLayout.isRefreshing = true
    }

    override fun hideProgress() {
        recycler?.visibility = View.VISIBLE
        refreshLayout.isRefreshing = false
        errorTV.visibility = View.GONE
    }

    override fun displayRepos(repos: ArrayList<Repo>) {
        adapter.setData(repos)
    }

    override fun showError(t: Throwable) {
        refreshLayout.isRefreshing = false
        recycler?.visibility = View.GONE
        errorTV.visibility = View.VISIBLE
    }

}