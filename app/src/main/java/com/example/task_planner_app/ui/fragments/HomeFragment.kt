package com.example.task_planner_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_planner_app.R
import com.example.task_planner_app.databinding.FragmentHomeBinding
import com.example.task_planner_app.repository.remote.dto.TaskDto
import com.example.task_planner_app.ui.adapter.TaskAdapter
import com.example.task_planner_app.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    //private lateinit var taskAdapter : TaskAdapter
    lateinit var taskAdapter: TaskAdapter
    //private val taskAdapter = TaskAdapter()
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Init view
        initView()
        initObservers()
        initData()


      //  viewModel.taskList
        //setRecyclerView()
        return binding.root
    }

    /**
     * Method created to initialize all needed observers
     */
    private fun initObservers(){
        // Observe
        viewModel.successLiveData.observe(viewLifecycleOwner, {
            if (it) {

            }
        })

        viewModel.taskList.observe(viewLifecycleOwner, {
            // validate is task list is not empty
            if(it.isNotEmpty()){
                createHomeList(taskList = it)
            } else {
                Toast.makeText(requireContext(),"Empty List", Toast.LENGTH_LONG).show()
            }

        })


    }

    private fun initData(){
        viewModel.getTasks()
    }

    private fun initView(){
        binding.fabCreateTask.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createTaskFragment)
        }
    }

    private fun createHomeList(taskList : List<TaskDto>){
        // create adapter instance and set it to taskAdapter class variable
        taskAdapter = TaskAdapter(taskList = taskList)

        // prepare recycler view
        prepareRecyclerView()

        // set adapter with tasks to recyclerView
        binding.recyclerView.apply {
            adapter = taskAdapter
        }
    }

    private fun prepareRecyclerView(){
        // Create linear layout
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.apply {
            reverseLayout = true
            stackFromEnd = true
        }
        // Set linear layout to recyclerView
        binding.recyclerView.layoutManager = linearLayoutManager

    }

}