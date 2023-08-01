package com.example.noah.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R
import androidx.navigation.fragment.findNavController
import com.example.noah.DBManager
import retrofit2.Retrofit
import java.text.FieldPosition

class HomeFragment : Fragment(R.layout.fragment_home) {


    lateinit var recyclerView:RecyclerView
    lateinit var  myButton:Button
    lateinit var writeButton:Button
    lateinit var sqliteDB:SQLiteDatabase

    private val dataList= mutableListOf<HomeViewModel>()
    private val adapter = BoardAdapter(dataList)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView=view.findViewById(R.id.main_recycler_view)

        return view
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setContentView(R.layout.activity_main)

        val writeButton = view.findViewById<Button>(R.id.main_write_button)
        writeButton.setOnClickListener {
            //글쓰기 화면으로 전환
            findNavController().navigate(R.id.navigation_boardWrite)
        }
        val whatToDoButton = view.findViewById<Button>(R.id.home_button_action)
        whatToDoButton.setOnClickListener {
            //조치사항 화면으로 전환
            findNavController().navigate(R.id.navigation_whatToDo)
        }
        val myButton = view.findViewById<Button>(R.id.main_My_button)
        myButton.setOnClickListener {
            //내 정보 화면으로 전환
            findNavController().navigate(R.id.navigation_my_profile)
        }


        val boradDbManager = DBManager(requireContext())




        sqliteDB=boradDbManager.readableDatabase
        val cursor:Cursor
        cursor=sqliteDB.rawQuery("SELECT * FROM board;",null)
        while(cursor.moveToNext()) {
            val id=cursor.getString(cursor.getColumnIndex("id")).toString()
            val title = cursor.getString(cursor.getColumnIndex("title")).toString()
            val contents = cursor.getString(cursor.getColumnIndex("contents")).toString()
            dataList.add(HomeViewModel(id,title, contents))
            Log.d("dataList", dataList.toString())
        }

        cursor.close()
        sqliteDB.close()
        boradDbManager.close()

        adapter.setItemClickListener(object: BoardAdapter.OnItemClickListner {
            override fun onClick(v:View,position: Int){
                var clickedItem=dataList[position]
                val itmeTitle=clickedItem.title
                val itemId=clickedItem.title
                val itemContents=clickedItem.contents
                val intent=Intent(context,Comment::class.java)
                intent.putExtra("itmeTitle",itmeTitle)
                intent.putExtra("itemId",itemId)
                intent.putExtra("itemContents",itemContents)
                startActivity(intent)

            }
        })

        adapter.notifyDataSetChanged()

        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)
        recyclerView.adapter = adapter

    }


}
