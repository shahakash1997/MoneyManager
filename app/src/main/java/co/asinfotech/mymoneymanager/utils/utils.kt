package co.asinfotech.mymoneymanager.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager


object utils {
    fun hideKeyboard(activity: Activity) {
        val view: View? = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun isEmpty(str: Editable?): Boolean {
        return str.isNullOrEmpty() || str.isBlank()
    }

    fun isEmpty(str: String?): Boolean {
        return str.isNullOrEmpty() || str.isBlank()
    }
}