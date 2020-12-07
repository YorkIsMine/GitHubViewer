package com.yorkismine.githubviewer.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.yorkismine.githubviewer.base.NavigationActivity
import com.yorkismine.githubviewer.R

class MainActivity : NavigationActivity(R.layout.activity_main) {
    override fun containerId(): Int = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem, url: String) {
        if (item.itemId == R.id.share) {
            val sharingIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
            }
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }
}