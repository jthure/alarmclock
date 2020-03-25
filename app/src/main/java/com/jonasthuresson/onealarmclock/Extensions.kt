package com.jonasthuresson.onealarmclock

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

fun Context.uriFromResourceId(id: Int): Uri {
    return Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${this.packageName}/${id}")
}