package com.notableFactory.marvelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class SuperHero(
    val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val thumbnailExt: String,
    val comics: List<String>
) : Parcelable