package com.example.miniproject2starter.ui.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.miniproject2starter.data.Task
import com.example.miniproject2starter.data.TaskDao
import com.example.miniproject2starter.databinding.FragmentTaskBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private val tasks = mutableListOf<Task>()
    private lateinit var adapter: TaskAdapter
    private var taskId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = TaskAdapter(tasks,
            onCheck = { task ->
                task.isDone = !task.isDone
                adapter.notifyDataSetChanged()
            },
            onDelete = { task ->
                tasks.remove(task)
                adapter.notifyDataSetChanged()
            }
        )
        binding.rvTasks.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val title = binding.edtTask.text.toString()
            if (title.isNotBlank()) {
                val newTask = Task(taskId++, title, getTodayDate())
                tasks.add(newTask)
                adapter.notifyDataSetChanged()
                binding.edtTask.text.clear()
                Log.d("TaskFragment", "Tambah ditekan: $title")
            } else {
                Log.d("TaskFragment", "Tambah ditekan, tapi tidak sukses")
            }
        }
    }

    private fun getTodayDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
