package com.faridjeyhunhuseyinteymur.newsly.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val id: String?,
    val name: String
) : Parcelable


// TODO : Will be used for PART 2
