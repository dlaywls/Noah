package com.example.noah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch

class MyProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        val notificationSwitch: Switch = findViewById(R.id.notificationSwitch)

        // 스위치 상태 변경 리스너를 설정합니다.
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            // isChecked 값에 따라 알림 On/Off 기능을 처리하면 됩니다.
            if (isChecked) {
                // 알림을 켜는 처리를 여기에 구현합니다.
                // 예: 알림 기능을 활성화하는 코드
            } else {
                // 알림을 끄는 처리를 여기에 구현합니다.
                // 예: 알림 기능을 비활성화하는 코드
            }
        }
    }
}
