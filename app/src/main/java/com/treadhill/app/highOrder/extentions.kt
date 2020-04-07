package com.treadhill.app.highOrder

import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

/**
 * SimpleDateFormat for EEE, MMM d
 */
val sdfDate: SimpleDateFormat = if (Build.VERSION.SDK_INT >= 24) SimpleDateFormat("EEE, MMM d", Locale.getDefault(Locale.Category.FORMAT)) else SimpleDateFormat("EEE, MMM d")

/**
 * SimpleDateFormat for d MMM, h : mm a
 */
val sdfDateTime: SimpleDateFormat = if (Build.VERSION.SDK_INT >= 24) SimpleDateFormat("d MMM, h : mm a", Locale.getDefault(Locale.Category.FORMAT)) else SimpleDateFormat("h : mm a")

/**
 * Copy Calender Instance
 *
 * @return Calender
 */
fun Calendar.copy(): Calendar {
    val cal: Calendar = Calendar.getInstance()
    cal.timeInMillis = this.timeInMillis
    return cal
}

/**
 *
 * @return Date in EEE, MMM d format
 */
fun Calendar.getDateInFormat(): String = sdfDate.format(this.time)
/**
 *
 * @return Date and Time in d MMM, h : mm a format
 */
fun Calendar.getDateTimeInFormat(): String = sdfDateTime.format(this.time)

/**
 * Display snackbar on Fragment
 *
 * @param msg
 * @param duration optional
 * @param actionTitle optional
 * @param onClick optional
 */
fun Fragment.showSnackbar(msg: String, duration: Int = Snackbar.LENGTH_SHORT, actionTitle: String? = null, onClick: (View) -> Unit = { Unit }) {
    val snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content), msg, duration)
    if (actionTitle != null && onClick != { Unit }) snackbar.setAction(actionTitle, onClick)
    snackbar.show()
}

/**
 * Display snackbar on Activity
 *
 * @param msg
 * @param duration optional
 * @param actionTitle optional
 * @param onClick optional
 */
fun AppCompatActivity.showSnackbar(msg: String, duration: Int = Snackbar.LENGTH_SHORT, actionTitle: String? = null, onClick: (View) -> Unit = { Unit }) {
    val snackbar = Snackbar.make(this.findViewById(android.R.id.content), msg, duration)
    if (actionTitle != null && onClick != { Unit }) snackbar.setAction(actionTitle, onClick)
    snackbar.show()
}

fun View.convertDptoPx(dp: Int): Int =
    (dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

/**
 * find Calories for HeartRate in LHS
 *
 * @param age
 * @param weight in KG
 * @param gender M or F
 * @param time in Hours
 */
fun Int.findCalories(age: Int, weight: Int, gender: String, time: Float) =
    (if (gender == "M") ((-55.0969 + (0.6309 * this) + (0.1988 * weight) + (0.2017 * age)) / 4.184) * 60 * time else ((-20.4022 + (0.4472 * this) - (0.1263 * weight) + (0.074 * age)) / 4.184) * 60 * time).toInt()
