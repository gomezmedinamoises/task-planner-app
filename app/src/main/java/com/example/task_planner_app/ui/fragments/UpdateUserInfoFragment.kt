package com.example.task_planner_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task_planner_app.databinding.FragmentUpdateUserInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateUserInfoFragment : Fragment() {

    private lateinit var binding: FragmentUpdateUserInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateUserInfoBinding.inflate(inflater, container, false)

        return binding.root
    }
}