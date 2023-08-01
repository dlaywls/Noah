package com.example.noah.ui.home

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.DBManager
import com.example.noah.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Comment : Fragment() {


    lateinit var commentsRecyclerView:RecyclerView
    lateinit var commentsAdapter : CommentAdapter
    lateinit var sqliteDB: SQLiteDatabase
    lateinit var sendButton:ImageButton
    lateinit var titleTextView:TextView
    lateinit var contentsTextView:TextView
    lateinit var commentsEditText: EditText
    
    val dataList= mutableListOf<CommentModel>()

    lateinit var commentDBManager: DBManager


    private var itemBoard_id: Long? =null
    private var itemTitle: String? = null
    private var itemContents: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_comment, container, false)

        commentDBManager = DBManager(requireContext())

        commentsRecyclerView = view.findViewById(R.id.commnets_recyclerView)
        sendButton=view.findViewById(R.id.img_send)
        commentsEditText=view.findViewById(R.id.comments_editText)
        titleTextView=view.findViewById(R.id.comments_item_title_text)
        contentsTextView=view.findViewById(R.id.comments_item_contents_text)


        //번들의 데이터 가져옴
        itemBoard_id=arguments?.getLong("itemId")
        itemContents=arguments?.getString("itemContents")
        itemTitle=arguments?.getString("itemTitle")

        Log.d("item", itemBoard_id.toString())
        Log.d("item", itemContents.toString())
        Log.d("item", itemTitle.toString())

        // 가져온 데이터를 텍스트뷰에 설정
        titleTextView.text = itemTitle
        contentsTextView.text = itemContents


        return view
    }
    @SuppressLint("Range")
    private fun loadDataFromDB() {
        dataList.clear()
        GlobalScope.launch(Dispatchers.IO) {
            val db = commentDBManager.readableDatabase
            val cursor: Cursor
            cursor = db.rawQuery("SELECT * FROM commentsDB WHERE board_id='$itemBoard_id';", null)
            while (cursor.moveToNext()) {
                val board_id = cursor.getLong(cursor.getColumnIndex("board_id"))
                val comments = cursor.getString(cursor.getColumnIndex("comments")).toString()
                dataList.add(CommentModel(null, board_id, comments))
                Log.d("comment: dataList", dataList.toString())
            }

            cursor.close()
            db.close()
            commentDBManager.close()

            // 어댑터에 데이터 변경을 알리는 코드
            withContext(Dispatchers.Main) {
                commentsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        //보내기 버튼 누르면 테이블에 데이터 삽입
        sendButton.setOnClickListener {
            val strComments = commentsEditText.text.toString().trim()

            sqliteDB=commentDBManager.writableDatabase
            if (strComments.isNotEmpty()) {
                // 데이터 삽입 (파라미터 바인딩 사용)
                val sql = "INSERT INTO commentsDB(board_id, comments) VALUES(?, ?);"
                val args = arrayOf(itemBoard_id, strComments)
                GlobalScope.launch(Dispatchers.IO) {
                    commentDBManager.writableDatabase.execSQL(sql, args)
                }
                // 삽입 후 댓글 목록을 갱신
                loadDataFromDB()
            } else {
                Toast.makeText(context, "댓글을 입력하세요.", Toast.LENGTH_SHORT).show()
            }


        }
        // 댓글 목록을 로드하고 어댑터 설정
        loadDataFromDB()
        commentsAdapter = CommentAdapter(dataList)
        commentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        commentsRecyclerView.adapter = commentsAdapter

    }



}