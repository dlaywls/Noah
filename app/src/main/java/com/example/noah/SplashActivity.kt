package com.example.noah

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noah.ui.home.HomeFragment


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            Thread.sleep(2000) // splash 화면 대기 시간
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        startActivity(Intent(this, MainActivity::class.java)) // splash 화면이 끝난 뒤 띄울 Activity

        finish()
    }
}
