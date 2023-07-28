package com.example.noah.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R

class BoardAdapter(private val dataList: List<HomeViewModel>) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.item_title_text)
        val contentsTextView: TextView = itemView.findViewById(R.id.item_contents_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return BoardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val data = dataList[position]
        holder.titleTextView.text = data.title
        holder.contentsTextView.text = data.contents
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}