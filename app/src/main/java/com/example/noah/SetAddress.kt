package com.example.noah

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class SetAddress : AppCompatActivity() {

    lateinit var mEtAddress: EditText
    lateinit var button_setAddress: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_address)

        mEtAddress = findViewById(R.id.et_address)
        button_setAddress = findViewById(R.id.button_set)

        //block touch
        mEtAddress.isFocusable ?: false
        mEtAddress.setOnClickListener {
            //주소 검색 웹뷰 화면으로 이동
            val intent = Intent(this@SetAddress, SearchRoadActivity::class.java)
            getSearchResult.launch(intent)
        }

        // 확인 버튼 누르면 마이프로필 화면으로 다시 돌아가기
        button_setAddress.setOnClickListener() {
            //마이프로필로 다시 이동
            val intent = Intent(this, MyProfile::class.java)
            getSearchResult.launch(intent)

        }
    }

    private val getSearchResult = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        //SearchRoad Activity로부터의 결과 값이 이곳으로 전달
        if (result.resultCode == RESULT_OK) {
            if (result.data != null) {
                val data = result.data!!.getStringExtra("data")
                mEtAddress.setText(data)


            }
        }
    }
}
