package co.asinfotech.mymoneymanager.utils

import android.util.Log

object Tracer {
    fun debug(TAG: String, message: String) {
        Log.d(TAG, message)
    }
}