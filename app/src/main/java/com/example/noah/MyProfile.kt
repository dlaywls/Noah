package com.example.noah

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import com.kakao.sdk.user.UserApiClient

class MyProfile : AppCompatActivity() {

    private lateinit var logoutButton: LinearLayout
    private lateinit var deleteIdButton: LinearLayout
    private lateinit var notificationSwitch: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        logoutButton = findViewById(R.id.logoutButton)
        deleteIdButton = findViewById(R.id.deleteIdButton)
        notificationSwitch = findViewById(R.id.notificationSwitch)

        logoutButton.setOnClickListener {

            // 로그아웃
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                }
                else {
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                }
            }
        }

        deleteIdButton.setOnClickListener {

            // 회원탈퇴
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Log.e(TAG, "연결 끊기 실패", error)
                }
                else {
                    Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                }
            }

        }

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

    companion object {
        private const val TAG = "MyProfileActivity"
    }
}





