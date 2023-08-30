package com.example.noah.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient

class NotificationsFragment : Fragment() {


    // This property is only valid between onCreateView and
    // onDestroyView.

    val dataList = mutableListOf<NotifyModel>()
    lateinit var notifyAdapter : NotifyAdapter
    lateinit var notifyRecyclerView: RecyclerView
    val db = Firebase.firestore
    var board_user_id:Long=0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view=inflater.inflate(R.layout.fragment_notifications, container, false)
        notifyRecyclerView = view.findViewById(R.id.recyclerview_comment)

        //회원번호 가져오기
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.me { user, error ->
                board_user_id = user?.id!!
                Log.d("login_o", board_user_id.toString())
            }
        }

        dataList.clear()
        //달린 댓글 데이터리스트에 넣기
        db.collection("comments")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if(document.getData().get("board_user_id").toString()==board_user_id.toString()){
                        val comments=document.getData().get("comments").toString()
                        dataList.add(NotifyModel(comments))
                        Log.d("NotifyDataList", dataList.toString())
                    }

                }
                notifyAdapter.notifyDataSetChanged()
            }

        // 댓글 목록을 로드하고 어댑터 설정
        notifyAdapter = NotifyAdapter(dataList)
        notifyRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        notifyRecyclerView.adapter = notifyAdapter

        return view
    }


}