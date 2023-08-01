package com.example.noah.ui.notifications

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R

class NotifyAdapter(private val dataList: List<NotifyModel>)
    : RecyclerView.Adapter<NotifyAdapter.NotifyViewHolder>() {

    inner class NotifyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NotifyComment: TextView = itemView.findViewById(R.id.NotifyComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_notification,parent,false)
        return NotifyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NotifyViewHolder, position: Int) {
        val item = dataList[position]
        //댓글 보여주기
        holder.NotifyComment.text=item.comments
        Log.d("onBindViewHolder",item.comments)
    }
}