package com.example.affiliates_android.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.affiliates_android.R
import com.example.affiliates_android.databinding.FragmentAddOrDeleteBinding

class AddOrDeleteFragment : Fragment() {
    private var _binding: FragmentAddOrDeleteBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddOrDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    // TODO: 2022-09-20 ViewBinding, BottomNavigation, ViewModel - Templete
}