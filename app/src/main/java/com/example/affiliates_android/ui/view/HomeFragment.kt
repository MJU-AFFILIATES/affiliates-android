package com.example.affiliates_android.ui.view

import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.affiliates_android.data.repository.UserRepositoryImpl
import com.example.affiliates_android.databinding.FragmentHomeBinding
import com.example.affiliates_android.ui.viewmodel.UserViewModel
import com.example.affiliates_android.ui.viewmodel.UserViewModelProviderFactory
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPOIItem

import net.daum.mf.map.api.MapPoint
import java.util.*


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    // viewModel 초기화
    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // viewModel 사용
        val userRepository = UserRepositoryImpl()
        val factory = UserViewModelProviderFactory(userRepository)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        val mapView = MapView(context)
        binding.mapView.addView(mapView)

        // Geocoder 객체 생성
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.580153641583045, 126.9228129200726), true)
        mapView.setZoomLevel(1, true)
        mapView.zoomIn(true)
        mapView.zoomOut(true)
        val MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(getLating("인천광역시 부평구 후정동로12").latitude, getLating("인천광역시 부평구 후정동로12").longitude)
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

//    @OptIn(DelicateCoroutinesApi::class)
//    suspend fun LatLng.getAddress(context: Context): String {
//        kotlin.runCatching {
//            val address = GlobalScope.async(Dispatchers.IO) {
//                val address = Geocoder(context, Locale.KOREA).getFromLocation(latitude, longitude, 1)
//
//            }
//        }
//
//    }












    // input: 도로명주소 output: 위도, 경도
    private fun getLating(address:String) : LatLng {
        val geoCoder = Geocoder(requireContext(), Locale.KOREA)

        val list = geoCoder.getFromLocationName(address, 3)

        var location = LatLng(37.506483,126.742024)

        if (list != null){
            if (list.size == 0 ) {
                Log.d("homeo-geo", "주소X")
            } else {
                val addressLatLng = list[0]
                location = LatLng(addressLatLng.latitude, addressLatLng.longitude)
                return location
            }
        }

        return location
    }

    //위도 경도로 주소 구하는 Reverse-GeoCoding
    private fun getAddress(position: LatLng): String {
        val geoCoder = Geocoder(requireContext(), Locale.KOREA)
        var addr = "주소 오류"

        //GRPC 오류? try catch 문으로 오류 대처
        try {
            addr = geoCoder.getFromLocation(position.latitude, position.longitude, 1)!!.first()
                .getAddressLine(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return addr
    }
}