package com.treadhill.app.highOrder

import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


val sdfDate: SimpleDateFormat = if (Build.VERSION.SDK_INT >= 24) SimpleDateFormat("EEE, MMM d", Locale.getDefault(Locale.Category.FORMAT)) else SimpleDateFormat("EEE, MMM d")
val sdfTime: SimpleDateFormat = if (Build.VERSION.SDK_INT >= 24) SimpleDateFormat("h : mm a", Locale.getDefault(Locale.Category.FORMAT)) else SimpleDateFormat("h : mm a")
val sdfDateTime: SimpleDateFormat = if (Build.VERSION.SDK_INT >= 24) SimpleDateFormat("d MMM, h : mm a", Locale.getDefault(Locale.Category.FORMAT)) else SimpleDateFormat("h : mm a")

fun Calendar.copy(): Calendar {
    val cal: Calendar = Calendar.getInstance()
    cal.timeInMillis = this.timeInMillis
    return cal
}

fun Calendar.getDateInFormat(): String = sdfDate.format(this.time)
fun Calendar.getTimeInFormat(): String = sdfTime.format(this.time)
fun Calendar.getDateTimeInFormat(): String = sdfDateTime.format(this.time)
fun Calendar.getMonthInFormat(): String =
    "${this.get(Calendar.MONTH) + 1} / ${this.get(Calendar.YEAR)}"

fun Calendar.getCalEnderWithMillis(millis: Long): Calendar {
    this.timeInMillis = millis
    return this
}

fun Fragment.showSnackbar(msg: String, duration: Int = Snackbar.LENGTH_SHORT, actionTitle: String? = null, onClick: (View) -> Unit = { Unit }) {
    val snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content), msg, duration)
    if (actionTitle != null && onClick != { Unit }) snackbar.setAction(actionTitle, onClick)
    snackbar.show()
}

fun AppCompatActivity.showSnackbar(msg: String, duration: Int = Snackbar.LENGTH_SHORT, actionTitle: String? = null, onClick: (View) -> Unit = { Unit }) {
    val snackbar = Snackbar.make(this.findViewById(android.R.id.content), msg, duration)
    if (actionTitle != null && onClick != { Unit }) snackbar.setAction(actionTitle, onClick)
    snackbar.show()
}

fun View.convertPxtoDp(px: Int): Int =
    (px / (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun View.convertDptoPx(dp: Int): Int =
    (dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun Int.findCalories(age: Int, weight: Int, gender: String, time: Float) =
    (if (gender == "M") ((-55.0969 + (0.6309 * this) + (0.1988 * weight) + (0.2017 * age)) / 4.184) * 60 * time else ((-20.4022 + (0.4472 * this) - (0.1263 * weight) + (0.074 * age)) / 4.184) * 60 * time).toInt()