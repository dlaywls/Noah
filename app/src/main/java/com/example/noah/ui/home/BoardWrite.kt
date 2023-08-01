package com.example.noah.ui.home


import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.database.sqlite.SQLiteDatabase
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
import com.example.noah.DBManager
import com.example.noah.R
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.AccessTokenInfo
import kotlin.properties.Delegates


class BoardWrite() : Fragment() {

    lateinit var writeTitleEdit: EditText
    lateinit var writeContentsEdit: EditText
    lateinit var registButton: Button
    lateinit var sqlitedb:SQLiteDatabase

    //임시 id
    lateinit var writeIdEdit:EditText
    var strId: Long = 0
    var id:Long=0


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_board_write, container, false)

        writeTitleEdit = view.findViewById(R.id.title_edit)
        writeContentsEdit = view.findViewById(R.id.contents_edit)

        writeIdEdit = view.findViewById(R.id.id_edit_test)

        registButton = view.findViewById(R.id.regist_button)


        val boardDbManager = DBManager(requireContext())


        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.me { user, error ->
                strId = user?.id!!
                Log.d("login_o", strId.toString())
            }
        }

        registButton.setOnClickListener {

            val random = (1..1000).random()  // 1 <= n <= 20
            id= random.toLong()
            //val strId=writeIdEdit.text.toString().trim()
            val strTitle = writeTitleEdit.text.toString().trim()
            val strContents = writeContentsEdit.text.toString().trim()
            //var strId:Long=0



            Log.d("login_o", strId.toString())
            sqlitedb=boardDbManager.writableDatabase
            if (strTitle.isNotEmpty() && strContents.isNotEmpty()) {
                // 데이터 삽입
                sqlitedb.execSQL("INSERT INTO board VALUES('"+id+"','"+strId+"','"+strTitle+"','"+strContents+"');")
                Toast.makeText(context, "등록 완료", Toast.LENGTH_SHORT).show()
                Log.d("Write",strId.toString())

            } else {
                Toast.makeText(context, "글을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            sqlitedb.close()

            //커뮤니티 메인 화면으로 전환
            findNavController().navigate(R.id.navigation_home)


        }
        return view
    }
    //fun accessTokenInfo(callback: (tokenInfo: AccessTokenInfo?, error: Throwable?) -> Unit) {}


}