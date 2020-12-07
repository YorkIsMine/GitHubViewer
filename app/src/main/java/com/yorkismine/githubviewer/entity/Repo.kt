package com.yorkismine.githubviewer.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    val id: Long = 0,
    val name: String = "",
    val owner: Owner,
    @SerializedName("html_url")
    @Expose
    val htmlUrl: String = "",
    val description: String? = ""
) : Parcelable