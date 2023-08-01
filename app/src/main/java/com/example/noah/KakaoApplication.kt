package com.example.noah

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

internal class KakaoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "349bfd72de9758c2ea3eca3d73454c59")
    }
}