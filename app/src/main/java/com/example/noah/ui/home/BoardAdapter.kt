package com.example.noah.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.MainActivity
import com.example.noah.R

class BoardAdapter(val context: Context, private val dataList: List<HomeViewModel>,val fragment_s: HomeFragment) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    val items=ArrayList<HomeViewModel>()
    private var activity:MainActivity?=null

    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.item_title_text)
        val contentsTextView: TextView = itemView.findViewById(R.id.item_contents_text)
        fun bind(data:HomeViewModel, context: Context){

            titleTextView.text = data.title
            contentsTextView.text = data.contents
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return BoardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.bind(dataList[position],context)
        val data = dataList[position]
        //holder.titleTextView.text = data.title
        //holder.contentsTextView.text = data.contents

        holder.itemView.setOnClickListener{
            var fragment:Fragment=Comment()
            var bundle:Bundle=Bundle()
            bundle.putString("itemId",data.id)
            bundle.putString("itemTitle",holder.titleTextView.text.toString())
            bundle.putString("itemContents",holder.contentsTextView.text.toString())

            fragment.arguments=bundle

            activity = fragment_s.activity as MainActivity?
            activity?.fragmentChange_for_adapter(fragment)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}