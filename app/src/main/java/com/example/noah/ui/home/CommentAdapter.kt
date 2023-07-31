package com.example.noah.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R

class CommentAdapter(private val dataList: List<CommentModel>)
    : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentsTextView: TextView = itemView.findViewById(R.id.item_comments_text)

        fun bind(item:CommentModel){
            commentsTextView.text=item.comments
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

}