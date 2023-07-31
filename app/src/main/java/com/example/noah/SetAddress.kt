package com.example.noah

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class SetAddress : AppCompatActivity() {

//    lateinit var getAddress: EditText // EditText 변수를 lateinit으로 선언
      lateinit var mEtAddress: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_address)

        mEtAddress = findViewById(R.id.et_address)

        //block touch
        mEtAddress.isFocusable ?: false
        mEtAddress.setOnClickListener {
            //주소 검색 웹뷰 화면으로 이동
            val intent = Intent(this@SetAddress, SearchRoadActivity::class.java)
            getSearchResult.launch(intent)
        }


//        getAddress = findViewById(R.id.getAddress) // findViewById는 onCreate 내부에서 호출
//
//        getAddress.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // 입력하기 전에 호출되는 메서드
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // 입력 중일 때 호출되는 메서드
//                val text = s.toString()
//                // 여기서 입력 중인 텍스트를 처리하면 됩니다.
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                // 입력이 완료된 후에 호출되는 메서드
//            }
//        })
    }

    private val getSearchResult = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        //SearchRoad Activity로부터의 결과 값이 이곳으로 전달
        if (result.resultCode == RESULT_OK) {
            Log.d("dataResult", "data 없음")
            if (result.data != null) {
                Log.d("dataResult", "data 읽기")
                val data = result.data!!.getStringExtra("data")
                Log.d("dataResult", data.toString())
//                val data = result.data?.getStringExtra("data")
                mEtAddress.setText(data)
            }
        }
    }

}