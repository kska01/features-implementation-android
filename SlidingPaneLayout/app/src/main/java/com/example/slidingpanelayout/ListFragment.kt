package com.example.slidingpanelayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slidingpanelayout.adapter.ListPaneAdapter
import com.example.slidingpanelayout.data.ColorData
import com.example.slidingpanelayout.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding
        get() = _binding!!

    private val data = ColorData().loadColorData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recyclerview 설정 및 시작
        initRecyclerview()

    }

    private fun initRecyclerview() {
        binding.listPaneRecyclerview.apply {
            adapter = ListPaneAdapter(requireContext(), data)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}