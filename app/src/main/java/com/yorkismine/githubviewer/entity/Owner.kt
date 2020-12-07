package com.yorkismine.githubviewer.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val login: String = ""
) : Parcelable