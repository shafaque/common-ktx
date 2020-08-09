package com.shaf.commonktx.lib

import android.content.SharedPreferences

fun SharedPreferences.getInt(pair: Pair<String, Int>): Int =
    this.getInt(pair.first, pair.second)

fun SharedPreferences.getBool(pair: Pair<String, Boolean>): Boolean =
    this.getBoolean(pair.first, pair.second)

fun SharedPreferences.getString(pair: Pair<String, String>): String? =
    this.getString(pair.first, pair.second)

fun SharedPreferences.setInt(value: Int, pair: Pair<String, Int>) =
    edit { it.putInt(pair.first, value) }

fun SharedPreferences.setBool(value: Boolean, pair: Pair<String, Boolean>) =
    edit { it.putBoolean(pair.first, value) }

fun SharedPreferences.setString(value: String?, pair: Pair<String, String>) =
    edit { it.putString(pair.first, value) }


inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}