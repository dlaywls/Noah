package com.example.noah

<<<<<<< Updated upstream
=======
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
>>>>>>> Stashed changes
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class SetAddress : AppCompatActivity() {

<<<<<<< Updated upstream
    lateinit var getAddress: EditText // EditText 변수를 lateinit으로 선언
=======
//    lateinit var getAddress: EditText // EditText 변수를 lateinit으로 선언
      lateinit var mEtAddress: EditText
      lateinit var button_setAddress: Button
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_address)

<<<<<<< Updated upstream
        getAddress = findViewById(R.id.getAddress) // findViewById는 onCreate 내부에서 호출

        getAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 입력하기 전에 호출되는 메서드
=======
        mEtAddress = findViewById(R.id.et_address)
        button_setAddress = findViewById(R.id.button_setAddress)

        //block touch
        mEtAddress.isFocusable ?: false
        mEtAddress.setOnClickListener {
            //주소 검색 웹뷰 화면으로 이동
            val intent = Intent(this@SetAddress, SearchRoadActivity::class.java)
            getSearchResult.launch(intent)
        }

        // 확인 버튼 누르면 마이프로필 화면으로 다시 돌아가기
        button_setAddress.setOnClickListener(){
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
>>>>>>> Stashed changes
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