package com.shaf.commonktx.lib

import android.content.Context
import android.widget.Toast


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(id: Int) =
    Toast.makeText(this, resources.getString(id), Toast.LENGTH_SHORT).show()

