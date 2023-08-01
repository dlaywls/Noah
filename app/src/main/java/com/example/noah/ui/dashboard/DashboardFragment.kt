package com.example.noah.ui.dashboard

import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noah.databinding.FragmentDashboardBinding
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
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        context ?: return binding.root

       //val mapView = MapView(context)
       //binding.mapView.addView(mapView)


        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

            return binding.root
        }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}