package com.notableFactory.marvelapp.utils

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

object Ui {

    fun closeKeyboard(view: View, activity: Activity) {
        if (view != null) {
            val inputMethodManager =
                activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}