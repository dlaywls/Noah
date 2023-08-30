package com.example.noah.ui.home

import android.annotation.SuppressLint
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
import com.example.noah.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient


class Comment : Fragment() {


    lateinit var commentsRecyclerView:RecyclerView
    lateinit var commentsAdapter : CommentAdapter
    val db = Firebase.firestore
    lateinit var sendButton:ImageButton
    lateinit var titleTextView:TextView
    lateinit var contentsTextView:TextView
    lateinit var commentsEditText: EditText
    
    val dataList= mutableListOf<CommentModel>()



    private var itemBoardId: String? =null
    private var itemTitle: String? = null
    private var itemContents: String? = null
    private var itemUserId: String? = null
    var commentsUserId: Long=0

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_comment, container, false)

        commentsRecyclerView = view.findViewById(R.id.commnets_recyclerView)
        sendButton=view.findViewById(R.id.img_send)
        commentsEditText=view.findViewById(R.id.comments_editText)
        titleTextView=view.findViewById(R.id.comments_item_title_text)
        contentsTextView=view.findViewById(R.id.comments_item_contents_text)


        //번들의 데이터 가져옴
        itemBoardId=arguments?.getString("itemId")
        itemContents=arguments?.getString("itemContents")
        itemTitle=arguments?.getString("itemTitle")
        itemUserId=arguments?.getString("itemUserId")

        Log.d("item", itemBoardId.toString())
        Log.d("item", itemContents.toString())
        Log.d("item", itemTitle.toString())

        //회원번호 가져오기
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.me { user, error ->
                commentsUserId = user?.id!!
                Log.d("login_o", commentsUserId.toString())
            }
        }

        // 글 타이틀과 내용을 댓글 화면에서 보여줌
        titleTextView.text = itemTitle
        contentsTextView.text = itemContents

        return view
    }


    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //보내기 버튼 누르면 데이터 삽입
        sendButton.setOnClickListener {
            //editText에서 댓글 가져오기
            val strComments = commentsEditText.text.toString().trim()

            //EditText에 글이 있으면
            if (strComments.isNotEmpty()) {
                // 게시판 아이디, 댓글, 데이터 삽입
                val board_id=db.collection("boards").document().getId()

                val comment = hashMapOf(
                    "comments_id" to board_id,
                    "board_id" to itemBoardId,
                    "board_user_id" to itemUserId,
                    "comments" to strComments,
                    "comments_user_id" to commentsUserId
                )

                db.collection("comments")
                    .document()
                    .set(comment)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(context, "등록", Toast.LENGTH_SHORT).show()
                        //Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "등록 실패", Toast.LENGTH_SHORT).show()
                        //Log.w(TAG, "Error adding document", e)
                    }

                val bundle=Bundle()
                bundle.putString("comments",strComments)
                Log.d("strComments의 값 : ", strComments)
                // 삽입 후 댓글 목록을 갱신
                loadDataFromDB()

            //EditText에 글이 없을 때
            } else {
                Toast.makeText(context, "댓글을 입력하세요.", Toast.LENGTH_SHORT).show()
            }


        }
        // 댓글 목록을 로드하고 어댑터 설정
        loadDataFromDB()
        commentsAdapter = CommentAdapter(dataList)
        commentsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        commentsRecyclerView.adapter = commentsAdapter

    }

    @SuppressLint("Range")
    //댓글 목록 로드
    private fun loadDataFromDB() {

        //데이터 리스트 초기화
        dataList.clear()


        db.collection("comments")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if(document.getData().get("board_id").toString()==itemBoardId){
                        val commentsId =document.getData().get("comments_id").toString()
                        val boardId=document.getData().get("board_id").toString()
                        val comments=document.getData().get("comments").toString()
                        dataList.add(CommentModel(commentsId,boardId,comments))
                        Log.d("dataList", dataList.toString())
                    }

                }
                commentsAdapter.notifyDataSetChanged()
            }

        }
    }
