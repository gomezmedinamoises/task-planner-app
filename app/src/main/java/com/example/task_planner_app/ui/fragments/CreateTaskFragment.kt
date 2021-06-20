package com.example.task_planner_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_planner_app.R
import com.example.task_planner_app.databinding.FragmentCreateTaskBinding
import com.example.task_planner_app.ui.dialog.DatePickerFragment
import com.example.task_planner_app.viewmodel.MainActivityViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

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

        val statusList = resources.getStringArray(R.array.status_options)
        var selectedStatus = statusList.first()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, statusList)
        binding.insertStatusTask.adapter = adapter

        binding.insertStatusTask.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedStatus = statusList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.insertDateTask.setOnClickListener {
            showDatePickerDialog()
        }

        binding.fabSaveTask.setOnClickListener {
            val description = binding.insertDescriptionTask.text.toString()
            val responsible = binding.insertResponsibleTask.text.toString()
            val date = binding.insertDateTask.text.toString()
            val status = binding.insertStatusTask.selectedItem.toString()
            validateCreateTaskFields(description, responsible, date, status)
            viewModel.successLiveData.observe(viewLifecycleOwner, {
                goHomeFragmentFromCreateTaskFragment()
                FancyToast.makeText(activity, "The task was created successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show()
            })
            viewModel.createTask(description, responsible, date, status)
        }

        return binding.root
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        activity?.let { datePicker.show(it.supportFragmentManager, "datePicker") }
    }

    fun onDateSelected(day: Int, month: Int, year:Int) {
        binding.insertDateTask.setText("$year/$month/$day")
    }

    private fun goHomeFragmentFromCreateTaskFragment() {
        findNavController().navigate(R.id.action_createTaskFragment_to_homeFragment)
    }

    private fun validateCreateTaskFields(description: String, responsible: String, date: String, status: String): Boolean {
        if (description.isEmpty() || responsible.isEmpty() || date.isEmpty() || status.isEmpty()) {
            FancyToast.makeText(activity, "Please, fill all fields", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show()
            return false
        } else {
            return true
        }
    }
}