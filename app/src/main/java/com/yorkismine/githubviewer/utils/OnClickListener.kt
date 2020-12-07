package com.yorkismine.githubviewer.utils

import com.yorkismine.githubviewer.entity.Repo

interface OnClickListener {
    fun onClick(repo: Repo)
}