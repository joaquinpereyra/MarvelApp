package com.notableFactory.marvelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelComic(
    val title: String,
    val issueNumber: String,
    val description: String,
    val thumbnailUrlExt: String,
    val thumbnailUrlPath: String,
    val characters: List<String>
) : Parcelable
