package co.asinfotech.mymoneymanager

import android.content.Context
import androidx.multidex.MultiDexApplication
import co.asinfotech.mymoneymanager.config.Config
import co.asinfotech.mymoneymanager.utils.Tracer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : MultiDexApplication() {
    companion object {
        private const val TAG = Config.LOGGER_TAG
        private lateinit var appContext: Context
        fun getAppContext(): Context {
            return appContext
        }
    }

    override fun onCreate() {
        Tracer.debug(TAG, " onCreate ")
        super.onCreate()
        appContext = applicationContext
    }

}