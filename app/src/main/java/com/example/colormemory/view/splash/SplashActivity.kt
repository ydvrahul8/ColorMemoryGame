package com.example.colormemory.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.colormemory.R
import com.example.colormemory.view.home.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
        scope.launch {
            delay(1500)
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        scope.cancel()
        super.onPause()
    }

}