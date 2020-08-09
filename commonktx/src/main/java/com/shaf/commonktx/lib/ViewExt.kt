package com.shaf.commonktx.lib

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun EditText.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(getTrimmedText()) && Patterns.EMAIL_ADDRESS.matcher(getTrimmedText())
        .matches()
}

inline fun EditText.onKeyBoardDonePress(crossinline action: () -> Unit) {
    setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
            action()
        }
        true
    }
}

inline fun TextView.multiColorText(
    lineSplitLength: Int,
    @ColorRes firstTextColor: Int,
    @ColorRes secondTextColor: Int,
    crossinline onClick: () -> Unit
) {

    val word = SpannableString(this.text.toString())

    word.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(this.context, secondTextColor)),
        0, lineSplitLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    word.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(this.context, firstTextColor)),
        lineSplitLength + 1, word.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    val spanClick = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClick()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = true
        }
    }

    word.setSpan(spanClick, 0, lineSplitLength, 0)
    this.movementMethod = LinkMovementMethod.getInstance()
    this.text = word
}

/**
 * Password Cannot have spaces and cannot start with a special character or number.
 *
 */
fun EditText.isValidPassword(): Boolean {

    val whiteSpace = text.matches(Regex(".*[^\\S].*"))
    val digitStart = text.matches(Regex("^[0-9]*"))
    val specialCharStart = text.matches(Regex("^[!@#\$%^&*()\\-_=+{}|?>.<,:;~`’/].*"))

    return whiteSpace || digitStart || specialCharStart
}

fun EditText.isValidPasswordLength(min: Int, max: Int): Boolean {
    return this.text.matches(Regex(".*.{$min,$max}"))
}

fun EditText.getTrimmedText() = this.text.toString().trim()

fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.underDev() {
    this.context.toast(this.context.resources.getString(R.string.under_dev))
}
