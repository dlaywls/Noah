package com.example.noah.ui.home

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R
import androidx.navigation.fragment.findNavController
import com.example.noah.DBManager

class HomeFragment : Fragment(R.layout.fragment_home) {


    lateinit var recyclerView:RecyclerView
    lateinit var  myButton:Button
    lateinit var writeButton:Button
    lateinit var sqlitedDB:SQLiteDatabase
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView=view.findViewById(R.id.main_recycler_view)
        myButton=view.findViewById(R.id.main_My_button)
        writeButton=view.findViewById(R.id.main_write_button)

        return view
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setContentView(R.layout.activity_main)

        val button = view.findViewById<Button>(R.id.main_write_button)
        button.setOnClickListener {
            //글쓰기 화면으로 전환
            findNavController().navigate(R.id.navigation_boardWrite)
        }


        val dbManager = DBManager(requireContext())



        val dataList= mutableListOf<HomeViewModel>()

        sqlitedDB=dbManager.readableDatabase
        val cursor:Cursor
        cursor=sqlitedDB.rawQuery("SELECT * FROM board;",null)
        while(cursor.moveToNext()) {
            var id=cursor.getInt(cursor.getColumnIndex("id"))
            var title = cursor.getString(cursor.getColumnIndex("title")).toString()
            var contents = cursor.getString(cursor.getColumnIndex("contents")).toString()
            dataList.add(HomeViewModel(id,title, contents))
            Log.d("dataList", dataList.toString())
        }
        //여기까지 잘 됨

        cursor.close()
        sqlitedDB.close()
        dbManager.close()


        val adapter = MainAdapter(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    class MainAdapter(private val dataList: List<HomeViewModel>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.findViewById(R.id.item_title_text)
            val contentsTextView: TextView = itemView.findViewById(R.id.item_contents_text)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
            return MainViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val data = dataList[position]
            holder.titleTextView.text = data.title
            holder.contentsTextView.text = data.contents
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }

}
