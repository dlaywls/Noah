package com.example.noah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class SetAddress : AppCompatActivity() {

    lateinit var getAddress: EditText // EditText 변수를 lateinit으로 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_address)

        getAddress = findViewById(R.id.getAddress) // findViewById는 onCreate 내부에서 호출

        getAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 입력하기 전에 호출되는 메서드
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 입력 중일 때 호출되는 메서드
                val text = s.toString()
                // 여기서 입력 중인 텍스트를 처리하면 됩니다.
            }

            override fun afterTextChanged(s: Editable?) {
                // 입력이 완료된 후에 호출되는 메서드
            }
        })
    }
}