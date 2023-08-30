package com.example.noah.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R
import androidx.navigation.fragment.findNavController
import com.example.noah.MainActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(R.layout.fragment_home) {


    lateinit var recyclerView:RecyclerView
    val db = Firebase.firestore
    val dataList= mutableListOf<HomeViewModel>()
    private lateinit var adapter:BoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_home, container, false)

        //리사이클러뷰 초기화
        recyclerView=view.findViewById(R.id.main_recycler_view)

        return view
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //글쓰기 화면으로 전환
        val writeButton = view.findViewById<Button>(R.id.main_write_button)
        writeButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_boardWrite)
        }

        //조치사항 화면으로 전환
        val whatToDoButton = view.findViewById<Button>(R.id.home_button_action)
        whatToDoButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_whatToDo)
        }

        //내 정보 화면으로 전환
        val myButton = view.findViewById<Button>(R.id.main_My_button)
        myButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_my_profile)
        }


        //데이터 읽기
        db.collection("boards")
            .get()
            .addOnSuccessListener { result ->
                dataList.clear()
                for (document in result) {
                    val userId =document.getData().get("user_id").toString()
                    val boardId=document.getData().get("board_id").toString()
                    val title=document.getData().get("title").toString()
                    val contents=document.getData().get("contents").toString()

                    dataList.add(HomeViewModel(userId,boardId,title, contents))
                    Log.d("dataList", dataList.toString())
                }
                adapter.notifyDataSetChanged()

            }
        val mActivity=activity as MainActivity
        adapter = BoardAdapter(dataList)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)
        recyclerView.adapter = adapter



        //글 클릭했을 때 해당 글 데이터 bundle에 넣고 comment프래그먼트로 이동
        adapter.setItemClickListener(object: BoardAdapter.OnItemClickListner {
            override fun onClick(v:View,position: Int){
                val clickedItem=dataList[position]
                val itmeTitle=clickedItem.title
                val itemId=clickedItem.id
                val itemContents=clickedItem.contents
                val itemUserId=clickedItem.user_id

                val fragment:Fragment=Comment()

                //bundle에 넣기
                val bundle =Bundle()
                if (itemId != null) {
                    bundle.putString("itemId",itemId)
                }
                bundle.putString("itemTitle",itmeTitle)
                bundle.putString("itemContents",itemContents)
                bundle.putString("itemUserId",itemUserId)

                mActivity.fragmentChange_for_adapter(fragment)
                Log.d("setItemClickListener","클릭 됨")
                findNavController().navigate(R.id.navigation_comment,bundle)

            }
        })


    }


}
