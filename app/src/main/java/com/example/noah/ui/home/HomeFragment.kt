package com.example.noah.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noah.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    //private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    lateinit var recyclerView:RecyclerView
    lateinit var adapter:MainAdapter
    lateinit var  my_button:Button
    lateinit var write_button:Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView=view.findViewById(R.id.main_recycler_view)
        my_button=view.findViewById(R.id.main_My_button)
        write_button=view.findViewById(R.id.main_write_button)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        val dataList = createDummyDataList()
        adapter = MainAdapter(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun createDummyDataList(): List<HomeViewModel> {
        val dataList = mutableListOf<HomeViewModel>()
        dataList.add(HomeViewModel(null,"Title 1", "contents1"))
        dataList.add(HomeViewModel(null,"Title 2", "contents2"))
        dataList.add(HomeViewModel(null,"Title 3", "contents3"))
        dataList.add(HomeViewModel(null,"Title 4", "contents4"))
        dataList.add(HomeViewModel(null,"Title 5", "contents5"))
        dataList.add(HomeViewModel(null,"Title 6", "contents6"))
        return dataList
    }


    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
    class MainAdapter(private val dataList: List<HomeViewModel>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        init {
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
            return MainViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val data = dataList[position]
            holder.mTitleTextView.text = data.title
            holder.mContentsTextView.text = data.contents
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val mTitleTextView: TextView = itemView.findViewById(R.id.item_title_text)
            val mContentsTextView: TextView = itemView.findViewById(R.id.item_contents_text)
        }
    }



}
