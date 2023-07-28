package com.example.noah.ui.home


import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.noah.DBManager
import com.example.noah.R


class BoardWrite() : Fragment() {

    lateinit var writeTitleEdit: EditText
    lateinit var writeContentsEdit: EditText
    lateinit var registButton: Button
    lateinit var sqlitedb:SQLiteDatabase





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_board_write, container, false)

        writeTitleEdit = view.findViewById(R.id.title_edit)
        writeContentsEdit = view.findViewById(R.id.contents_edit)
        registButton = view.findViewById(R.id.regist_button)


        val dbManager = DBManager(requireContext())

        registButton.setOnClickListener {
            val strTitle = writeTitleEdit.text.toString().trim()
            val strContents = writeContentsEdit.text.toString().trim()

            sqlitedb=dbManager.writableDatabase
            if (strTitle.isNotEmpty() && strContents.isNotEmpty()) {
                // 데이터 삽입
                sqlitedb.execSQL("INSERT INTO board(title,contents) VALUES('"+strTitle+"','"+strContents+"');")

            } else {
                //입력 값 비어있을 경우 처리
            }
            sqlitedb.close()

            //커뮤니티 메인 화면으로 전환
            findNavController().navigate(R.id.navigation_home)


        }
        return view
    }


}