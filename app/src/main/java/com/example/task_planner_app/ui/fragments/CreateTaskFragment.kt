package com.example.task_planner_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_planner_app.R
import com.example.task_planner_app.databinding.FragmentCreateTaskBinding
import com.example.task_planner_app.viewmodel.MainActivityViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTaskFragment : Fragment() {

    private lateinit var binding: FragmentCreateTaskBinding
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false)

        binding.fabSaveTask.setOnClickListener {
            val description = binding.insertDescriptionTask.text.toString()
            val responsible = binding.insertResponsibleTask.text.toString()
            val date = binding.insertDateTask.text.toString()
            val status = binding.insertStatusTask.text.toString()
            validateCreateTaskFields(description, responsible, date, status)
            viewModel.createTask(description, responsible, date, status)
            goHomeFragmentFromCreateTaskFragment()
        }

        return binding.root
    }

    private fun goHomeFragmentFromCreateTaskFragment() {
        findNavController().navigate(R.id.action_createTaskFragment_to_homeFragment)
    }

    private fun validateCreateTaskFields(description: String, responsible: String, date: String, status: String) {
        if (description.isEmpty() && responsible.isEmpty() && date.isEmpty() && status.isEmpty()) {
            FancyToast.makeText(activity, "Please, fill all fields", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show()
        }
    }
}