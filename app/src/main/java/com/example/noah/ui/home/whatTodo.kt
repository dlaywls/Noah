package com.example.noah.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.noah.R


class whatTodo : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_what_todo, container, false)
    }
    fun onClickEarthquake() {
        Log.i("whatTodo", "onClickEarthquake")
        Toast.makeText(context, "지진 발생 시", Toast.LENGTH_SHORT).show()
    }
    fun onClickPortalSite() {
        Log.i("whatTodo", "onClickPortalSite")
        Toast.makeText(context, "국민 재난 안전 포털", Toast.LENGTH_SHORT).show()
        //웹 페이지 열기 위한 intent
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.safekorea.go.kr/"))
        startActivity(intent)
    }



}