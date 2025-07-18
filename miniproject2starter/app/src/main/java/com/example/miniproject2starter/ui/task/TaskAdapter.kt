package com.example.miniproject2starter.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniproject2starter.data.Task
import com.example.miniproject2starter.databinding.ItemTaskBinding

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onCheck: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.txtTitle.text = task.title
        holder.binding.tvDate.text = task.date
        holder.binding.checkbox.isChecked = task.isDone

        holder.binding.checkbox.setOnClickListener { onCheck(task) }
        holder.binding.btnDelete.setOnClickListener { onDelete(task) }
    }

    override fun getItemCount(): Int = tasks.size
}
