package com.wahyuashari.simpletodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyuashari.simpletodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var todoListRecyclerViewAdapter: TodoListRecyclerViewAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        todoListRecyclerViewAdapter = TodoListRecyclerViewAdapter(mutableListOf())
        binding.recyclerView.adapter = todoListRecyclerViewAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.addButton.setOnClickListener {
            if(!binding.inputTodo.text.toString().isEmpty()) {
                todoListRecyclerViewAdapter.addTodo(binding.inputTodo.text.toString())
                binding.inputTodo.text.clear()
            }
        }
        binding.deleteButton.setOnClickListener {
            todoListRecyclerViewAdapter.deleteDone()
        }
        setContentView(binding.root)
    }
}