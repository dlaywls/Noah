package com.example.noah.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noah.databinding.FragmentDashboardBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //MapView 띄우기
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val mapView = MapView(context)
        binding.mapView.addView(mapView)

        //마커 추가
        /*val apiData = TestApiData()
        val dataArr = apiData.getData()

        val markerArr = ArrayList<MapPOIItem>()
        for (data in dataArr!!) {
            val marker = MapPOIItem()
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(data.latitude!!, data.longitude!!)
            marker.itemName = data.value.toString()
            markerArr.add(marker)
        }
        mapView.addPOIItems(markerArr.toTypedArray())*/

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}