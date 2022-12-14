package com.example.viewpager2withtap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewpager2withtap.databinding.FragmentTab2Binding


class Tab2Fragment : Fragment() {

    private lateinit var binding: FragmentTab2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTab2Binding.inflate(inflater, container, false)
        return binding.root
    }

}