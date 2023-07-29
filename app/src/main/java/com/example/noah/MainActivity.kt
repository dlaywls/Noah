package com.example.noah

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.noah.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Handler


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val SPLASH_DELAY: Long = 2000 // 로고를 보여줄 시간(2초)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2초 후에 로고를 보여주는 화면을 종료하고 메인 화면 또는 로그인 화면으로 이동
        Handler().postDelayed({

            // 예시로 로그인 상태를 false로 가정한 경우
            val isLoggedIn = false

            // 로그인 상태에 따라 메인 화면 또는 로그인 화면으로 이동
            if (isLoggedIn) {
                startActivity(Intent(this@MainActivity, "{메인액티비티}"::class.java))
            } else {
                startActivity(Intent(this@MainActivity, login::class.java))
            }

            finish() // MainActivity를 종료하여 뒤로 가기 버튼을 눌렀을 때 로고 화면으로 돌아가지 않도록 함
        }, SPLASH_DELAY)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}