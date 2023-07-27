package com.example.noah;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "{NATIVE_APP_KEY}");
    }
}

