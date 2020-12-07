package com.yorkismine.githubviewer.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.yorkismine.githubviewer.ui.detail.DetailFragment
import com.yorkismine.githubviewer.ui.main.MainFragment

abstract class NavigationActivity(private val layoutResId: Int) : AppCompatActivity(), Depended {
    abstract fun containerId(): Int
    private var url: String = ""
    override fun setData(url: String) {
        this.url = url
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        if (savedInstanceState == null)
            goToFragment(MainFragment(), MainFragment.TAG)
    }

    override fun goToFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerId(), fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 0) supportFragmentManager
            .popBackStack(DetailFragment.TAG, POP_BACK_STACK_INCLUSIVE)
        else finish()
    }

    override fun hideToolbar() {
        supportActionBar?.hide()
    }

    override fun showToolbar(title: String) {
        supportActionBar?.show()
        supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onOptionsItemSelected(item, url)

        return true
    }

    abstract fun onOptionsItemSelected(item: MenuItem, url: String)
}