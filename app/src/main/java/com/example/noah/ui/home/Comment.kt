package com.example.noah.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R




class Comment : Fragment() {


    lateinit var commentsRecyclerView:RecyclerView
    lateinit var commentsAdapter : CommentAdapter
    val dataList= mutableListOf<CommentModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_comment, container, false)
        // RecyclerView 초기화
        commentsRecyclerView = view.findViewById(R.id.commnets_recyclerView)

        // 어댑터 초기화
        commentsAdapter = CommentAdapter(dataList)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        commentsRecyclerView.adapter = commentsAdapter

        // 여기서 newDataList를 원하는 데이터로 채우고, 어댑터에 데이터를 추가하는 등의 작업을 수행해야 합니다.
        // newDataList에 특정 데이터를 추가하는 예시:
        dataList.add(CommentModel(1, "This is item 1"))

        // 어댑터에 데이터 변경을 알리는 코드
        commentsAdapter.notifyDataSetChanged()
    }


}