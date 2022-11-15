package co.asinfotech.mymoneymanager.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import co.asinfotech.mymoneymanager.R

class MainActivity : AppCompatActivity() {
    private val handler: Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(
            { startActivity(Intent(this@MainActivity, LoginActivity::class.java)) },
            2000
        )

    }

    companion object {
        private const val TAG = "MainActivity"
    }
}