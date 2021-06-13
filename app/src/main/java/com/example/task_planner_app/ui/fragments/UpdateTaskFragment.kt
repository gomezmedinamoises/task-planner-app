package com.example.task_planner_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.set
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_planner_app.R
import com.example.task_planner_app.databinding.FragmentUpdateTaskBinding
import com.example.task_planner_app.repository.remote.dto.TaskDto
import com.example.task_planner_app.utils.JSONConverterUtil
import com.example.task_planner_app.utils.TASK_BUNDLE_KEY
import com.example.task_planner_app.viewmodel.MainActivityViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTaskFragment : Fragment() {

    private lateinit var binding: FragmentUpdateTaskBinding
    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var taskDto: TaskDto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateTaskBinding.inflate(inflater, container, false)

        viewModel.successLiveData.observe(viewLifecycleOwner, {
            goHomeFragmentFromUpdateTaskFragment()
            FancyToast.makeText(activity, "The task was updated successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show()
        })

        initData()

        binding.fabUpdateTask.setOnClickListener {
            // Get data from XML
            taskDto.description = binding.updateDescriptionTask.text.toString()
            taskDto.responsible = binding.updateResponsibleTask.text.toString()
            taskDto.date = binding.updateDateTask.text.toString()
            taskDto.status = binding.updateStatusTask.text.toString()
            // Call to View Model
            viewModel.updateTask(id = taskDto.id, taskDto = taskDto)
        }

        return binding.root
    }

    private fun populateData(taskDto: TaskDto) {
        binding.updateDescriptionTask.text.append(taskDto.description)
        binding.updateResponsibleTask.text.append(taskDto.responsible)
        binding.updateDateTask.text.append(taskDto.date)
        binding.updateStatusTask.text.append(taskDto.status)

    }

    private fun initData() {
        val taskString = arguments?.getString(TASK_BUNDLE_KEY)
        taskDto = JSONConverterUtil.fromJsonToObject(taskString!!, TaskDto::class.java)
        populateData(taskDto = taskDto)
    }

    private fun goHomeFragmentFromUpdateTaskFragment() {
        findNavController().navigate(R.id.action_updateTaskFragment_to_homeFragment)
    }
}