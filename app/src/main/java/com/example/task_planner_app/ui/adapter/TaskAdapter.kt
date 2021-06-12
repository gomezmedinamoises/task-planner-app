package com.example.task_planner_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task_planner_app.R
import com.example.task_planner_app.repository.model.entity.Task
import com.example.task_planner_app.repository.remote.dto.TaskDto
import kotlinx.android.synthetic.main.task_row.view.*

class TaskAdapter(
    private var taskList: List<TaskDto>
    ) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val taskDescription: TextView = view.findViewById(R.id.description_task)
            val taskResponsible: TextView = view.findViewById(R.id.responsible_task)
            val taskStatus: TextView = view.findViewById(R.id.status_task)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.itemView.apply {
            description_task.text = task.description
            responsible_task.text = task.responsible
            status_task.text = task.status
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateTaskList(task: List<TaskDto>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}
