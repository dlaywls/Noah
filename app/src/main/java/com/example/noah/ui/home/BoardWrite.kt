package com.example.noah.ui.home


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.noah.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient


class BoardWrite() : Fragment() {

    lateinit var writeTitleEdit: EditText
    lateinit var writeContentsEdit: EditText
    lateinit var registButton: Button
    val db = Firebase.firestore

    var user_id: Long = 0 //회원 번호
    var board_id:String =" "//글 id


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_board_write, container, false)

        writeTitleEdit = view.findViewById(R.id.title_edit)
        writeContentsEdit = view.findViewById(R.id.contents_edit)
        registButton = view.findViewById(R.id.regist_button)

        //회원번호 가져오기
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.me { user, error ->
                user_id = user?.id!!
                Log.d("login_o", user_id.toString())
            }
        }

        //등록 버튼 클릭
        registButton.setOnClickListener {


            board_id=db.collection("boards").document().getId()

            //에디트 텍스트의 Title, contents 받기
            val strTitle = writeTitleEdit.text.toString().trim()
            val strContents = writeContentsEdit.text.toString().trim()

            val board = hashMapOf(
                "board_id" to board_id,
                "user_id" to user_id,
                "title" to strTitle,
                "contents" to strContents
            )

            db.collection("boards")
                .document()
                .set(board)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(context, "업로드 성공", Toast.LENGTH_SHORT).show()
                    //Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "업로드 실패", Toast.LENGTH_SHORT).show()
                    //Log.w(TAG, "Error adding document", e)
                }


            //커뮤니티 메인 화면으로 전환
            findNavController().navigate(R.id.navigation_home)


        }
        return view
    }


}