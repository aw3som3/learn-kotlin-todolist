package com.wahyuashari.simpletodolist


import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wahyuashari.simpletodolist.databinding.TodoItemBinding

class TodoListRecyclerViewAdapter(
    private val todoList : MutableList<TodoModel>
) : RecyclerView.Adapter<TodoListRecyclerViewAdapter.TodoListViewHolder>() {

    private lateinit var binding: TodoItemBinding;

    class TodoListViewHolder(val itemBinding: TodoItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(todoItem : TodoModel){
            itemBinding.todoDescription.text = todoItem.description
            itemBinding.checkBox.isChecked = todoItem.checked
            itemBinding.checkBox.setOnCheckedChangeListener { compoundButton, checked ->
                todoItem.checked = checked
                if(checked){
                    itemBinding.todoDescription.paintFlags = itemBinding.todoDescription.paintFlags or STRIKE_THRU_TEXT_FLAG
                }else{
                    itemBinding.todoDescription.paintFlags = itemBinding.todoDescription.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }
    }

    fun addTodo(description : String, checked:Boolean = false){
        todoList.add(TodoModel(description,checked))
        notifyItemInserted(todoList.size-1)
    }

    fun deleteDone(){
        todoList.removeAll { todoItem -> todoItem.checked }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        var binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return TodoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        var todoItem = todoList[position]
        holder.bind(todoItem)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

}