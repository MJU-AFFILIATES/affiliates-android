package com.example.affiliates_android.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.affiliates_android.databinding.FragmentHomeBinding
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPOIItem

import net.daum.mf.map.api.MapPoint




class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val mapView = MapView(context)
        binding.mapView.addView(mapView)

        // 중심점 변경 - 명지대학교
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.580153641583045, 126.9228129200726), true)

        // 줌 레벨 변경
        mapView.setZoomLevel(1, true)

        // 줌 인
        mapView.zoomIn(true)

        // 줌 아웃
        mapView.zoomOut(true)

        /*마커 추가*/

        //마커 찍기 (영순이네)
        val MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(37.57932762578048, 126.923807185873)


        //

        val marker1 = MapPOIItem()


        marker1.itemName = "영순이네!"
        marker1.tag = 0

        // 좌표를 입력받아 현 위치로 출력
        marker1.mapPoint = MARKER_POINT1
        marker1.markerType = MapPOIItem.MarkerType.BluePin
        marker1.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView.addPOIItem(marker1)

        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



}