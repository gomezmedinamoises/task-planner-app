package com.example.task_planner_app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.task_planner_app.R
import com.example.task_planner_app.repository.remote.dto.TaskDto
import com.example.task_planner_app.ui.listeners.OnDeleteListener
import com.example.task_planner_app.utils.JSONConverterUtil
import com.example.task_planner_app.utils.TASK_BUNDLE_KEY
import kotlinx.android.synthetic.main.task_row.view.*

class TaskAdapter(
    private var taskList: List<TaskDto>,
    private val navController: NavController
    ) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private lateinit var context: Context
    lateinit var onDeleteListener: OnDeleteListener

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val taskDescription: TextView = view.findViewById(R.id.description_task)
            val taskResponsible: TextView = view.findViewById(R.id.responsible_task)
            val taskStatus: TextView = view.findViewById(R.id.status_task)
            val taskDate: TextView = view.findViewById(R.id.date_task)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_row, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.itemView.apply {
            description_task.text = task.description
            responsible_task.text = task.responsible
            status_task.text = task.status
            date_task.text = task.date
            button_menu_more.setOnClickListener { view ->
                setOptionMenuToItem(view, position, task, navController)
            }

        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateTaskList(task: List<TaskDto>) {
        this.taskList = task
        notifyDataSetChanged()
    }

    private fun setOptionMenuToItem(
        menuView: View,
        itemViewPosition: Int,
        taskDto: TaskDto,
        navController: NavController
    ) {
        val popupMenu = PopupMenu(context, menuView)
        popupMenu.inflate(R.menu.task_item_menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.delete_task -> {
                    // Obtain an ID for item deleted
                    onDeleteListener.onDeletePressed(id = taskDto.id, itemViewPosition = itemViewPosition)
                    true
                }

                R.id.update_task -> {
                    // Create a package with item data
                    val bundle = bundleOf(TASK_BUNDLE_KEY to JSONConverterUtil.fromObjectToJson(taskDto))
                    // Execute a navigation action
                    navController.navigate(R.id.updateTaskFragment, bundle)
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    fun removeItemFromTaskList(itemViewPosition: Int) {
        (taskList as ArrayList<TaskDto>).removeAt(itemViewPosition)
        notifyDataSetChanged()
    }


}
