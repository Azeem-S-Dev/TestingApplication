package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ExternalLinks(
    val facebook: List<UrlList>,
    val homepage: List<UrlList>,
    val instagram: List<UrlList>,
    val twitter: List<UrlList>,
    val wiki: List<UrlList>
) : Parcelable