package com.application_ruslang.ruslang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.application_ruslang.ruslang.model.Model
import com.application_ruslang.ruslang.view.MainActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMEOUT: Long = 1500;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
            //Model.instance
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIMEOUT)

    }


}
