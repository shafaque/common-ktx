package com.shaf.commonktx.lib

import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

fun String.youtubeIdToThumbNailURL() = "https://img.youtube.com/vi/$this/0.jpg"

fun String?.toDateFormat(
    inputDatePattern: String,
    outputDatePattern: String,
    default: Locale = Locale.getDefault(),
    defaultTimeZone: TimeZone = TimeZone.getTimeZone("UTC")
): String {
    if (this == null) return "N/A"
    return try {
        val dateFormat = SimpleDateFormat(inputDatePattern, Locale.getDefault())
        dateFormat.timeZone = defaultTimeZone
        val today = dateFormat.parse(this)
        SimpleDateFormat(outputDatePattern, default).format(today ?: Date())
    } catch (e: Exception) {
        "N/A "
    }
}

fun <T> String.deSerializeObject(classOfT: Class<T>): T {
    return Gson().fromJson(this, classOfT)
}

fun <T> T.serializeObject(): String {
    return Gson().toJson(this)
}

fun <T, E> T.mapTo(dest: Class<E>): E {
    return this.serializeObject().deSerializeObject(dest)
}