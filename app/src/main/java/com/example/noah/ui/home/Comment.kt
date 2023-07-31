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
import com.example.noah.Borad_DBManager
import com.example.noah.Comment_DBManager
import com.example.noah.R




class Comment : Fragment() {


    lateinit var commentsRecyclerView:RecyclerView
    lateinit var commentsAdapter : CommentAdapter
    lateinit var sqliteDB: SQLiteDatabase
    lateinit var sendButton:ImageButton
    lateinit var titleTextView:TextView
    lateinit var contentsTextView:TextView
    lateinit var commentsEditText: EditText

    val dataList= mutableListOf<CommentModel>()
    val itemTitle = arguments?.getString("itemTitle",null)
    val itemContents = arguments?.getString("itemContents",null)
    val commentDbManager = Comment_DBManager(requireContext())

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_comment, container, false)
        // RecyclerView 초기화
        commentsRecyclerView = view.findViewById(R.id.commnets_recyclerView)
        sendButton=view.findViewById(R.id.img_send)
        commentsEditText=view.findViewById(R.id.comments_editText)
        titleTextView=view.findViewById(R.id.comments_item_title_text)
        contentsTextView=view.findViewById(R.id.comments_item_contents_text)

        titleTextView.text=itemTitle
        titleTextView.text=itemContents




        sendButton.setOnClickListener {
            val strComments = commentsEditText.text.toString().trim()

            sqliteDB=commentDbManager.writableDatabase
            if (strComments.isNotEmpty()) {
                // 데이터 삽입
                sqliteDB.execSQL("INSERT INTO commentsDB VALUES('"+itemTitle+"','"+strComments+"');")

            } else {
                Toast.makeText(context, "댓글을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            sqliteDB.close()


        }


        return view
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sqliteDB=commentDbManager.readableDatabase
        val cursor: Cursor
        cursor=sqliteDB.rawQuery("SELECT * FROM commentDB WHERE title='$itemTitle';",null)
        while(cursor.moveToNext()) {
            val title=cursor.getString(cursor.getColumnIndex("title")).toString()
            val comments = cursor.getString(cursor.getColumnIndex("comments")).toString()
            dataList.add(CommentModel(title, comments))
            Log.d("comment: dataList", dataList.toString())
        }

        cursor.close()
        sqliteDB.close()
        commentDbManager.close()

        //dataList.add(CommentModel(1, "This is item 1"))

        commentsAdapter = CommentAdapter(dataList)
        commentsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        commentsRecyclerView.adapter = commentsAdapter
        // 어댑터에 데이터 변경을 알리는 코드
        //commentsAdapter.notifyDataSetChanged()
    }


}